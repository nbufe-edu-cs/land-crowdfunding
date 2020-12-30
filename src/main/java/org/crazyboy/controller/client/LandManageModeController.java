package org.crazyboy.controller.client;


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
@RequestMapping("/land/manage/mode")
public class LandManageModeController {

    @Resource
    private ILandManageModeService landManageModeService;

    /**
     * 获取土地管理模式
     *
     * @param landId
     * @return
     */
    @ApiOperation("获取土地管理模式")
    @GetMapping
    public ResponseResult getLandManageMode(@RequestParam("landId") String landId) {
        return landManageModeService.getLandManageMode(landId);
    }

} 

