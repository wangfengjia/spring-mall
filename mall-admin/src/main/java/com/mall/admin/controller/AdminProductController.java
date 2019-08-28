package com.mall.admin.controller;

import com.mall.admin.dto.AdminProductParams;
import com.mall.admin.service.IAdminProductService;
import com.mall.common.dto.RestResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Slf4j
@RequestMapping(value = "/admin/product")
public class AdminProductController {


    @Autowired
    private IAdminProductService productService;

    @ApiOperation(value = "新增商品")
    @ApiImplicitParam(name = "params", value = "新增商品参数", dataType = "AdminProductParams", required = true)
    @PostMapping(value = "/create")
    public RestResponse create(@RequestBody @Validated AdminProductParams params)
    {
        return null;
    }


}
