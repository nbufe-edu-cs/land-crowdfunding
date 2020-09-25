package org.crazyboy.controller;


import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.Land;
import org.crazyboy.service.ILandService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>
 * 土地表 前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/land")
public class LandController {

    @Resource
    private ILandService landService;

    /**
     * 土地封面照片上传
     *
     * @return
     */
    @PostMapping("/upload/img/cover")
    public ResponseResult uploadLandCoverImg(@RequestParam("cover") MultipartFile multipartFile,
                                             @RequestParam("landId") String landId) throws IOException {
        return landService.uploadLandCoverImg(multipartFile, landId);
    }

    /**
     * 保存土地基本信息
     *
     * @param land 土地实体
     * @return
     */
    @PostMapping("/save")
    public ResponseResult saveLand(@RequestBody Land land) {
        return landService.saveLand(land);
    }

    /**
     * 根据土地id查询土地信息
     *
     * @param landId 土地id
     * @return
     */
    @GetMapping
    public ResponseResult getLandById(@RequestParam("landId") String landId) {
        return landService.getLandById(landId);
    }

}

