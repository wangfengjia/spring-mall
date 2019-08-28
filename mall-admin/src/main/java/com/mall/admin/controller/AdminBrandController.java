package com.mall.admin.controller;

import com.mall.admin.dto.AdminBrandParams;
import com.mall.admin.dto.AdminBrandResult;
import com.mall.admin.service.IAdminBrandService;
import com.mall.common.dto.Pagination;
import com.mall.common.dto.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台品牌管理
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/23 23:27
 */
@RestController
@Validated
@Slf4j
@Api(tags = "AdminBrandController", value = "后台品牌管理")
@RequestMapping(value = "/admin/brand")
public class AdminBrandController {

    @Autowired
    private IAdminBrandService brandService;

    @ApiOperation(value = "新增品牌")
    @ApiImplicitParam(name = "params", value = "新增品牌参数", dataType = "AdminBrandParams", required = true)
    @PostMapping(value = "/add")
    public RestResponse add(@RequestBody @Validated AdminBrandParams params){
        try {
            brandService.add(params);
        }catch (Exception e){
            String message = "新增品牌失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "更新品牌信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录id", required = true),
            @ApiImplicitParam(name = "updateParams", value = "更新品牌参数", required = true)
    })
    @PostMapping(value = "/update/{id}")
    public RestResponse update(@PathVariable Long id,
                                @RequestBody @Validated AdminBrandParams updateParams
                               ){
        try {
            brandService.update(id, updateParams);
        }catch (Exception e){
            String message = "更新品牌失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "获取品牌列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "品牌名称", dataType = "String")
    })
    @GetMapping(value = "/get_list")
    public RestResponse<Pagination<AdminBrandResult>> getList(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(name = "name", required = false) String name
    ){
        Pagination<AdminBrandResult> brandList = brandService.getList(page, pageSize, name);
        return RestResponse.success(brandList);
    }

    @ApiOperation(value = "获取品牌详情")
    @ApiImplicitParam(name = "id", value = "记录id", required = true, dataType = "Long")
    @GetMapping(value = "/get_by_id")
    public RestResponse<AdminBrandResult> getById(@RequestParam(value = "id") Long id){
        AdminBrandResult brandResult = brandService.getById(id);
        return RestResponse.success(brandResult);
    }

    @ApiOperation(value = "批量删除品牌记录")
    @ApiImplicitParam(name = "ids", value = "记录id数组", required = true, dataType = "List<Long>")
    @PostMapping(value = "/batch_delete")
    public RestResponse batchDelete(@RequestParam("ids") List<Long> ids){
        try {
            brandService.batchDelete(ids);
        }catch (Exception e){
            String message = "批量删除记录失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "批量更新显示状态")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "记录id数组", required = true, dataType = "List<Long>"),
        @ApiImplicitParam(name = "showStatus", value = "显示状态值", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/update_show_status")
    public RestResponse updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus){
        try {
            brandService.updateShowStatus(ids, showStatus);
        }catch (Exception e){
            String message = "批量更新显示状态失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "记录id数组", required = true, dataType = "List<Long>"),
            @ApiImplicitParam(name = "factoryStatus", value = "制造商状态值", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/update_factory_status")
    public RestResponse updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus){

        try {
            brandService.updateFactoryStatus(ids, factoryStatus);
        }catch (Exception e){
            String message = "批量更新厂家制造商状态失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }
}
