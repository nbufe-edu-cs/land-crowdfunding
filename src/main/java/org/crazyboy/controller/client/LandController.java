package org.crazyboy.controller.client;

import io.swagger.annotations.ApiOperation;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.service.ILandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: kevin
 * @date: 10/28/20
 * @time: 1:18 PM
 * @description:
 **/

@RestController
@RequestMapping("/land")
public class LandController {

    @Resource
    private ILandService landService;

    /**
     * 获取所有众筹土地
     *
     * @param status
     * @return
     */
    @ApiOperation("获取土地列表")
    @GetMapping("/list")
    public ResponseResult listAllLand(@RequestParam(value = "status", required = false) Integer status) {
        return landService.listAllLand(status);
    }

    /**
     * 根据土地id获取土地详情
     *
     * @param landId
     * @return
     */
    @ApiOperation("获取土地详情")
    @GetMapping("/detail")
    public ResponseResult getLandById(@RequestParam(value = "landId") String landId) {
        return landService.getLandById(landId);
    }
}
