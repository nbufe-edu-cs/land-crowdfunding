package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandPlantFood;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
    ResponseResult listLandPlantFood(String landId);

    /**
     * 删除土地种植原料
     *
     * @param landId
     * @param plantFood
     * @return
     */
    ResponseResult deleteLandPlantFood(String landId, String plantFood);

    /**
     * 根据农产品id获取对应肥料列表
     *
     * @param productId
     * @return
     */
    ResponseResult<List<LandPlantFood>> getLandPlantFoodByProduct(String productId);
}
