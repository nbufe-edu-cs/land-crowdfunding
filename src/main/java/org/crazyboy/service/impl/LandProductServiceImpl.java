package org.crazyboy.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandProduct;
import org.crazyboy.mapper.LandProductMapper;
import org.crazyboy.service.ILandProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 土地种植农场品类表 服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Service
public class LandProductServiceImpl extends ServiceImpl<LandProductMapper, LandProduct>
        implements ILandProductService {

    /**
     * 添加土地种植农产品类别
     *
     * @param landProduct
     * @return
     */
    @Override
    public ResponseResult saveLandProduct(LandProduct landProduct) {
        LandProduct product = getOne(new LambdaQueryWrapper<LandProduct>()
                .eq(LandProduct::getProductName, landProduct.getProductName())
                .eq(LandProduct::getLandId, landProduct.getLandId()));
        if (!ObjectUtil.isEmpty(product)) {
            return ResponseResult.error(ResponseCode.L_P_EXIST.getCode(), ResponseCode.L_P_EXIST.getMsg());
        }
        landProduct.setProductId(IdUtil.objectId());
        if (save(landProduct)) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_P_SAVE_ERR.getCode(), ResponseCode.L_P_SAVE_ERR.getMsg());
    }

    /**
     * 获取土地种植农产品列表
     *
     * @param landId
     * @return
     */
    @Override
    public ResponseResult listLandProduct(String landId) {
        return ResponseResult.success("OK", list(new LambdaQueryWrapper<LandProduct>()
                .eq(LandProduct::getLandId, landId)));
    }

    /**
     * 删除土地种植农产品
     *
     * @param landId
     * @param productName
     * @return
     */
    @Override
    public ResponseResult deleteLandProduct(String landId, String productName) {
        LandProduct landProduct = getOne(new LambdaQueryWrapper<LandProduct>()
                .eq(LandProduct::getLandId, landId)
                .eq(LandProduct::getProductName, productName));
        if (ObjectUtil.isEmpty(landProduct)) {
            return ResponseResult.error(ResponseCode.L_P_NOT_EXIST.getCode(), ResponseCode.L_P_NOT_EXIST.getMsg());
        }
        if (remove(new LambdaQueryWrapper<LandProduct>()
                .eq(LandProduct::getLandId, landId)
                .eq(LandProduct::getProductName, productName))) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_P_DEL_ERR.getCode(), ResponseCode.L_P_DEL_ERR.getMsg());
    }
}
