package org.crazyboy.controller.manage;


import io.swagger.annotations.ApiOperation;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandPlantFood;
import org.crazyboy.service.ILandPlantFoodService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 土地种植原料表 前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/land/manage/plant/food")
public class ManageLandPlantFoodController {

    @Resource
    private ILandPlantFoodService landPlantFoodService;

    /**
     * 添加土地种植原料
     *
     * @param landPlantFood
     * @return
     */
    @ApiOperation("添加土地种植原料")
    @PostMapping
    public ResponseResult saveLandPlantFood(@RequestBody LandPlantFood landPlantFood) {
        return landPlantFoodService.saveLandPlantFood(landPlantFood);
    }

    /**
     * 获取土地种植原料列表
     *
     * @param landId
     * @return
     */
    @ApiOperation("获取土地种植原料列表")
    @GetMapping
    public ResponseResult listLandPlantFood(String landId) {
        return landPlantFoodService.listLandPlantFood(landId);
    }

    /**
     * 删除土地种植原料
     *
     * @param landId
     * @param plantFood
     * @return
     */
    @ApiOperation("删除土地种植原料")
    @DeleteMapping
    public ResponseResult deleteLandPlantFood(String landId, String plantFood) {
        return landPlantFoodService.deleteLandPlantFood(landId, plantFood);
    }

}

