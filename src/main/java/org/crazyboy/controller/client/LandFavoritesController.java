package org.crazyboy.controller.client;


import io.swagger.annotations.ApiOperation;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.service.ILandFavoritesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户土地收藏夹表 前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/land/favorites")
public class LandFavoritesController {

    @Resource
    private ILandFavoritesService landFavoritesService;

    /**
     * 用户添加土地到收藏夹
     *
     * @param userId
     * @param landId
     * @return
     */
    @ApiOperation("用户添加土地到收藏夹")
    @PostMapping
    public ResponseResult addLand2Favorites(@RequestParam("userId") Integer userId,
                                            @RequestParam("landId") String landId) {
        return landFavoritesService.addLand2Favorites(userId, landId);
    }

    /**
     * 用户将土地从收藏夹移除
     * @param userId
     * @param landId
     * @return
     */
    @ApiOperation("用户将土地从收藏夹移除")
    @DeleteMapping
    public ResponseResult removeLandFromFavorites(@RequestParam("userId") Integer userId,
                                                  @RequestParam("landId") String landId) {
        return landFavoritesService.removeLandFromFavorites(userId, landId);
    }

    /**
     * 获取收藏夹列表
     *
     * @param userId
     * @return
     */
    @ApiOperation("获取收藏夹列表")
    @GetMapping
    public ResponseResult listFavorites(@RequestParam("userId") Integer userId) {
        return landFavoritesService.listFavorites(userId);
    }

}

