package org.crazyboy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.crazyboy.entity.LandFavorites;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户土地收藏夹表 Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Mapper
public interface LandFavoritesMapper extends BaseMapper<LandFavorites> {

    /**
     * 根据用户id获取收藏夹中的土地ID列表
     * @param userId
     * @return
     */
    List<String> selectLandIdListByUserId(Integer userId);
}
