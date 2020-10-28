package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandFavorites;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户土地收藏夹表 服务类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
public interface ILandFavoritesService extends IService<LandFavorites> {

    /**
     * 用户添加土地到收藏夹
     *
     * @param userId
     * @param landId
     * @return
     */
    ResponseResult addLand2Favorites(Integer userId, String landId);

    /**
     * 用户将土地从收藏夹移除
     *
     * @param userId
     * @param landId
     * @return
     */
    ResponseResult removeLandFromFavorites(Integer userId, String landId);

    /**
     * 获取收藏夹列表
     *
     * @param userId
     * @return
     */
    ResponseResult listFavorites(Integer userId);
}
