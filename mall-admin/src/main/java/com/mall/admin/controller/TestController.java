package com.mall.admin.controller;

import com.mall.common.annotation.Mobile;
import com.mall.common.dto.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/index")
    public RestResponse index(@RequestParam(value = "mobile") @Mobile String mobile){
        return RestResponse.success(mobile);
    }
}
