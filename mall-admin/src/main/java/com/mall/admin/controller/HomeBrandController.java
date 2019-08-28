package com.mall.admin.controller;

import com.mall.admin.dto.HomeBrandParams;
import com.mall.admin.dto.HomeBrandResult;
import com.mall.admin.service.IHomeBrandService;
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
 * 首页推荐品牌管理
 * @date 2019/04/14 22:06
 * @author wangyongchun
 * @version 1.0
 */
@RestController
@Validated
@Slf4j
@Api(tags = "HomeBrandController", value = "首页推荐品牌管理")
@RequestMapping(value = "/home/brand")
public class HomeBrandController {

    @Autowired
    private IHomeBrandService brandService;

    @ApiOperation(value = "新增首页推荐品牌")
    @ApiImplicitParam(name = "params", value = "添加首页推荐品牌参数", dataType = "HomeBrandParams", required = true)
    @PostMapping(value = "/create")
    public RestResponse create(@RequestBody @Validated HomeBrandParams params){
        try {
            brandService.create(params);
        }catch (Exception e){
            String message = "添加首页推荐品牌失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "更新首页推荐品牌排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录id", dataType = "Long", required = true),
            @ApiImplicitParam(name = "sort", value = "排序值", dataType = "Integer", required = true)
    })
    @PostMapping(value = "/update_sort")
    public RestResponse updateSort(@RequestParam("id") Long id,
                                   @RequestParam("sort") Integer sort){
        try {
            brandService.updateSort(id, sort);
        }catch (Exception e){
            String message = "更新品牌排序失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "批量删除首页推荐品牌")
    @ApiImplicitParam(name = "ids", value = "记录id数组", required = true, dataType = "List<Long>")
    @PostMapping(value = "/batch_delete")
    public RestResponse batchDelete(@RequestParam("ids") List<Long> ids){
        try {
            brandService.batchDelete(ids);
        }catch (Exception e){
            String message = "批量删除首页推荐品牌失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }
        return RestResponse.success();
    }

    @ApiOperation(value = "更新首页推荐品牌的推荐状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "首页推荐品牌id", dataType = "Long", required = true),
            @ApiImplicitParam(name = "recommendStatus", value = "推荐状态值", dataType = "Integer", required = true)
    })
    @PostMapping(value = "/update_recommend_status")
    public RestResponse updateRecommendStatus(@RequestParam("id") Long id,
                                              @RequestParam("recommendStatus") Integer recommendStatus){
        try {
            brandService.updateRecommendStatus(id, recommendStatus);
        }catch (Exception e){
            String message = "更新首页推荐品牌的推荐状态失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "分页获取首页推荐品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandName", value = "品牌名称", dataType = "String"),
            @ApiImplicitParam(name = "recommendStatus", value = "推荐状态", dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据条数", dataType = "Integer", defaultValue = "20")
    })
    @GetMapping(value = "/get_list")
    public RestResponse<Pagination<HomeBrandResult>> getList(
            @RequestParam(value = "brandName", required = false) String brandName,
            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ){
        Pagination<HomeBrandResult> list = brandService.getList(brandName, recommendStatus, page, pageSize);
        return RestResponse.success(list);
    }
}
