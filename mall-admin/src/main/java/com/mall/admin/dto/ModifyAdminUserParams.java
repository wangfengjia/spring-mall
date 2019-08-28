package com.mall.admin.dto;

import com.mall.common.valid.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@ApiModel
public class ModifyAdminUserParams implements Serializable {

    @ApiModelProperty(value = "用户id", required = false)
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 12, message = "用户名的长度为1~12")
    private String username;


    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Size(min = 1, max = 16, message = "密码的长度为1~16")
    private String password;

    @ApiModelProperty(value = "用户头像", required = true)
    @NotBlank(message = "请上传头像")
    private String icon;

    @ApiModelProperty(value = "邮箱", required = false)
    private String email;

    @ApiModelProperty(value = "昵称", required = false)
    private String nickName;

    @ApiModelProperty(value = "备注", required = false)
    @Size(min = 1, max = 64, message = "备注的长度为1~64")
    private String note;


}
