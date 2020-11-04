package org.crazyboy.controller.manage;


import io.swagger.annotations.ApiOperation;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandManageMode;
import org.crazyboy.service.ILandManageModeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 土地管理模式表 前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/land/manage/manage/mode")
public class ManageLandManageModeController {

    @Resource
    private ILandManageModeService landManageModeService;

    /**
     * 添加种植管理模式
     *
     * @param landManageMode
     * @return
     */
    @ApiOperation("添加种植管理模式")
    @PostMapping
    public ResponseResult saveLandManageMode(@RequestBody LandManageMode landManageMode) {
        return landManageModeService.saveLandManageMode(landManageMode);
    }

    /**
     * 获取土地管理模式列表
     *
     * @param landId
     * @return
     */
    @ApiOperation("获取土地管理模式列表")
    @GetMapping
    public ResponseResult listLandManageMode(String landId) {
        return landManageModeService.listLandManageMode(landId);
    }

    /**
     * 删除土地管理模式
     *
     * @param landId
     * @param modeName
     * @return
     */
    @ApiOperation("删除土地管理模式")
    @DeleteMapping
    public ResponseResult deleteLandManageMode(String landId, String modeName) {
        return landManageModeService.deleteLandManageMode(landId, modeName);
    }

} 

