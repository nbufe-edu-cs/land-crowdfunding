package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandPlantFood;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 土地种植原料表 服务类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
public interface ILandPlantFoodService extends IService<LandPlantFood> {

    /**
     * 添加土地种植原料
     *
     * @param landPlantFood
     * @return
     */
    ResponseResult saveLandPlantFood(LandPlantFood landPlantFood);

    /**
     * 获取土地种植原料列表
     *
     * @return
     */
    ResponseResult listLandPlantFood();

    /**
     * 删除土地种植原料
     *
     * @param landPlantFoodId
     * @return
     */
    ResponseResult deleteLandPlantFood(Integer landPlantFoodId);
}
