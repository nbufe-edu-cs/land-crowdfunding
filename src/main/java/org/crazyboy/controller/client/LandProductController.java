package org.crazyboy.controller.client;


import io.swagger.annotations.ApiOperation;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandProduct;
import org.crazyboy.service.ILandProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 土地种植农场品类表 前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/land/product")
public class LandProductController {

    @Resource
    private ILandProductService landProductService;

    /**
     * 获取土地种植农产品列表
     *
     * @param landId
     * @return
     */
    @ApiOperation("获取土地种植农产品列表")
    @GetMapping
    public ResponseResult listLandProduct(String landId) {
        return landProductService.listLandProduct(landId);
    }
}

