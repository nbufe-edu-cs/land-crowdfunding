package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 土地种植农场品类表 服务类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
public interface ILandProductService extends IService<LandProduct> {

    /**
     * 添加土地种植农产品类别
     *
     * @param landProduct
     * @return
     */
    ResponseResult saveLandProduct(LandProduct landProduct);

    /**
     * 获取土地种植农产品列表
     *
     * @param landId
     * @return
     */
    ResponseResult<List<LandProduct>> listLandProduct(String landId);

    /**
     * 删除土地种植农产品
     *
     * @param landId
     * @param productName
     * @return
     */
    ResponseResult deleteLandProduct(String landId, String productName);
}
