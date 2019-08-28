package com.mall.admin.service.impl;

import com.mall.admin.dao.AdminLoginLogRepository;
import com.mall.admin.dao.AdminUserRepository;
import com.mall.admin.dto.AdminPermissionResult;
import com.mall.admin.dto.AdminUserResult;
import com.mall.admin.dto.ModifyAdminUserParams;
import com.mall.admin.entity.AdminLoginLog;
import com.mall.admin.entity.AdminUser;
import com.mall.admin.enums.AdminUserStatusEnum;
import com.mall.admin.mapper.UserMapper;
import com.mall.admin.service.IAdminPermissionService;
import com.mall.admin.service.IAdminUserService;
import com.mall.common.dto.Pagination;
import com.mall.common.exception.BusinessException;
import com.mall.common.specification.SimpleSpecificationBuilder;
import com.mall.common.specification.SpecificationOperator;
import com.mall.common.utils.JwtTokenUtils;
import com.mall.common.utils.ObjectMapperUtils;
import com.mall.common.utils.PaginationFormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminLoginLogRepository adminLoginLogRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IAdminPermissionService permissionService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public AdminUserResult getAdminByUsername(String username) {
        AdminUser adminUser = adminUserRepository.findAdminUserByUsername(username);
        return ObjectMapperUtils.map(adminUser, AdminUserResult.class);
    }

    @Override
    public void register(ModifyAdminUserParams adminParamsDTO) {
//        AdminUser adminUser = ObjectMapperUtils.map(adminParamsDTO, AdminUser.class);
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(adminParamsDTO, adminUser);
        System.out.println(adminUser.toString());
        AdminUser existAdmin = adminUserRepository.findAdminUserByUsername(adminUser.getUsername());
        if (existAdmin != null){
            throw new BusinessException("用户已存在");
        }

        String md5Password = passwordEncoder.encode(adminUser.getPassword());
        adminUser.setPassword(md5Password);
        adminUser.setStatus(AdminUserStatusEnum.ADMIN_USER_STATUS_NORMAL.getCode());

        adminUserRepository.save(adminUser);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, passwordEncoder.encode(password));
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = jwtTokenUtils.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        }catch (AuthenticationException e){
            log.warn("登录异常", e);
        }

        return token;
    }

    /**
    * 添加登录记录
    * @param username 用户名
    */
    private void insertLoginLog(String username){
        AdminUser adminUser = adminUserRepository.findAdminUserByUsername(username);
        AdminLoginLog adminLoginLog = new AdminLoginLog();
        adminLoginLog.setAdminId(adminUser.getId());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        adminLoginLog.setIp(request.getRemoteAddr());
        adminLoginLog.setAddress("");
        adminLoginLog.setUserAgent("");
        adminLoginLogRepository.save(adminLoginLog);
    }

    /**
    * 根据用户名修改登录时间
    * @param username 用户名
    */
    private void updateLoginTimeByUsername(String username){
        Timestamp loginTime = new Timestamp(System.currentTimeMillis());
        adminUserRepository.updateLoginTimeByUsername(loginTime, username);
    }

    /**
     * 刷新token
     * @param oldToken 老的token
     * @return
     */
    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtils.canRefresh(token)){
            return jwtTokenUtils.refreshToken(token);
        }
        return null;
    }

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return
     */
    @Override
    public AdminUserResult getAdminById(Long id) {
        Specification<AdminUser> condition = new SimpleSpecificationBuilder<AdminUser>()
                .addAnd("status", SpecificationOperator.Operator.EQ, AdminUserStatusEnum.ADMIN_USER_STATUS_NORMAL.getCode())
                .generateSpecification();
        AdminUser adminUser = adminUserRepository.findOne(condition).orElse(null);
        return ObjectMapperUtils.map(adminUser, AdminUserResult.class);
    }

    /**
     * 获取后天用户列表
     * @param username 用户名
     * @param page 页码
     * @param pageSize 每页的数据条数
     * @return
     */
    @Override
    public Pagination<AdminUserResult> getList(String username, Integer page, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        SimpleSpecificationBuilder builder = new SimpleSpecificationBuilder();
        builder.addAnd("status", SpecificationOperator.Operator.EQ, AdminUserStatusEnum.ADMIN_USER_STATUS_NORMAL.getCode());
        if (username != null){
            builder.addAnd("username", SpecificationOperator.Operator.LIKEALL, username);
        }
        Specification<AdminUser> specification = builder.generateSpecification();
        Page<AdminUser> users = adminUserRepository.findAll(specification, pageable);
        return PaginationFormatUtils.format(users, AdminUserResult.class);
    }

    /**
     * 更新用户信息
     * @param id 用户id
     * @param adminParamsDTO 用户信息
     * @return
     */
    @Override
    public AdminUserResult update(Long id, ModifyAdminUserParams adminParamsDTO) {
        AdminUser adminUser = adminUserRepository.findById(id).orElse(null);
        if (adminUser == null){
            throw new BusinessException("id为[" + id + "]的用户不存在");
        }
        AdminUser updateUser = new AdminUser();
        BeanUtils.copyProperties(adminParamsDTO, updateUser);
        updateUser.setId(id);
        AdminUser user = adminUserRepository.save(updateUser);
        return ObjectMapperUtils.map(user, AdminUserResult.class);
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    @Override
    public AdminUserResult delete(Long id) {
        AdminUserResult userResult = getAdminById(id);
        if (userResult == null){
            throw new BusinessException("id为[" + id + "]用户不存在");
        }
        AdminUser deleteUser = new AdminUser();
        BeanUtils.copyProperties(userResult, deleteUser);
        deleteUser.setStatus(AdminUserStatusEnum.ADMIN_USER_STATUS_FORBIDDEN.getCode());
        AdminUser user = adminUserRepository.save(deleteUser);
        return ObjectMapperUtils.map(user, AdminUserResult.class);
    }

    /**
     * 获取用户的所有权限
     * @param adminUserId 用户id
     * @return
     */
    @Override
    public List<AdminPermissionResult> getPermissionList(Long adminUserId) {
        return permissionService.getPermissionsByAdminId(adminUserId);
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<AdminUser> getAll() {
        List<AdminUser> users = userMapper.getAll();
        return users;
    }
}
