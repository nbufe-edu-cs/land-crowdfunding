package org.crazyboy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandFavorites;
import org.crazyboy.mapper.LandFavoritesMapper;
import org.crazyboy.service.ILandFavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.crazyboy.service.ILandService;
import org.crazyboy.vo.LandVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户土地收藏夹表 服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Service
public class LandFavoritesServiceImpl extends ServiceImpl<LandFavoritesMapper, LandFavorites>
        implements ILandFavoritesService {

    @Resource
    private LandFavoritesMapper landFavoritesMapper;

    @Resource
    private ILandService landService;

    /**
     * 用户添加土地到收藏夹
     *
     * @param userId
     * @param landId
     * @return
     */
    @Override
    public ResponseResult addLand2Favorites(Integer userId, String landId) {
        LandFavorites landFavorites = getOne(new LambdaQueryWrapper<LandFavorites>()
                .eq(LandFavorites::getLandId, landId)
                .eq(LandFavorites::getUserId, userId));
        if (!ObjectUtil.isEmpty(landFavorites)) {
            return ResponseResult.error(ResponseCode.L_F_EXIST.getCode(), ResponseCode.L_F_EXIST.getMsg());
        }
        if (save(new LandFavorites(userId, landId))) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_F_ADD_ERR.getCode(), ResponseCode.L_F_ADD_ERR.getMsg());
    }

    /**
     * 用户将土地从收藏夹移除
     *
     * @param userId
     * @param landId
     * @return
     */
    @Override
    public ResponseResult removeLandFromFavorites(Integer userId, String landId) {
        LandFavorites landFavorites = getOne(new LambdaQueryWrapper<LandFavorites>()
                .eq(LandFavorites::getLandId, landId)
                .eq(LandFavorites::getUserId, userId));
        if (ObjectUtil.isEmpty(landFavorites)) {
            return ResponseResult.error(ResponseCode.L_F_NOT_EXIST.getCode(), ResponseCode.L_F_NOT_EXIST.getMsg());
        }
        if (remove(new LambdaQueryWrapper<LandFavorites>()
                .eq(LandFavorites::getLandId, landId)
                .eq(LandFavorites::getUserId, userId))) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_F_REMOVE_ERR.getCode(), ResponseCode.L_F_REMOVE_ERR.getMsg());
    }

    /**
     * 获取收藏夹列表
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseResult listFavorites(Integer userId) {
        List<LandVO> result = new ArrayList<>();
        List<String> landIdLst = landFavoritesMapper.selectLandIdListByUserId(userId);
        landIdLst.forEach(landId -> {
            result.add((LandVO) landService.getLandInfo(landId).getData());
        });
        return ResponseResult.success("OK", result);
    }
}
