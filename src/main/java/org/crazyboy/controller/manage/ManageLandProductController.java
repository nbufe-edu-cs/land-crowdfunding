package org.crazyboy.controller.manage;


import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/land/manage/product")
public class ManageLandProductController {

    @Resource
    private ILandProductService landProductService;

    /**
     * 添加土地种植农产品类别
     *
     * @param landProduct
     * @return
     */
    @ApiOperation("添加土地种植农产品类别")
    @PostMapping
    public ResponseResult saveLandProduct(@RequestBody LandProduct landProduct) {
        return landProductService.saveLandProduct(landProduct);
    }

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

    /**
     * 删除土地种植农产品
     *
     * @param landId
     * @param productName
     * @return
     */
    @ApiOperation("删除土地种植农产品")
    @DeleteMapping
    public ResponseResult deleteLandProduct(String productId) {
        return landProductService.deleteLandProduct(productId);
    }

    /**
     * 修改土地种植农产品
     *
     * @param landProduct
     * @return
     */
    @ApiOperation("修改土地种植农产品")
    @PutMapping
    public ResponseResult updateLandProduct(@RequestBody LandProduct landProduct) {
        return landProductService.updateLandProduct(landProduct);
    }

}

