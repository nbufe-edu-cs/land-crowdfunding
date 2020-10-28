package org.crazyboy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandPlantFood;
import org.crazyboy.mapper.LandPlantFoodMapper;
import org.crazyboy.service.ILandPlantFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 土地种植原料表 服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Service
public class LandPlantFoodServiceImpl
        extends ServiceImpl<LandPlantFoodMapper, LandPlantFood>
        implements ILandPlantFoodService {

    /**
     * 添加土地种植原料
     *
     * @param landPlantFood
     * @return
     */
    @Override
    public ResponseResult saveLandPlantFood(LandPlantFood landPlantFood) {
        LandPlantFood plantFood = getOne(new LambdaQueryWrapper<LandPlantFood>()
                .eq(LandPlantFood::getPlantFood, landPlantFood.getPlantFood())
                .eq(LandPlantFood::getUserId, landPlantFood.getUserId()));
        if (!ObjectUtil.isEmpty(plantFood)) {
            return ResponseResult.error(ResponseCode.L_P_F_EXIST.getCode(), ResponseCode.L_P_F_EXIST.getMsg());
        }
        if (save(landPlantFood)) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_P_F_SAVE_ERR.getCode(), ResponseCode.L_P_F_SAVE_ERR.getMsg());
    }

    /**
     * 获取土地种植原料列表
     *
     * @return
     */
    @Override
    public ResponseResult listLandPlantFood() {
        return ResponseResult.success("OK", list());
    }

    /**
     * 删除土地种植原料
     *
     * @param landPlantFoodId
     * @return
     */
    @Override
    public ResponseResult deleteLandPlantFood(Integer landPlantFoodId) {
        LandPlantFood landPlantFood = getById(landPlantFoodId);
        if (ObjectUtil.isEmpty(landPlantFood)) {
            return ResponseResult.error(ResponseCode.L_P_F_NOT_EXIST.getCode(), ResponseCode.L_P_F_NOT_EXIST.getMsg());
        }
        if (removeById(landPlantFoodId)) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_P_F_DEL_ERR.getCode(), ResponseCode.L_P_F_DEL_ERR.getMsg());
    }
}
