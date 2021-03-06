package org.crazyboy.controller.manage;


import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.Land;
import org.crazyboy.model.MyPage;
import org.crazyboy.service.ILandService;
import org.crazyboy.vo.LandVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 土地表 前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/land/manage")
public class ManageLandController {

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
    @PostMapping
    public ResponseResult saveLand(@RequestBody Land land) {
        return landService.saveLand(land);
    }

    /**
     * 根据土地id查询土地信息
     *
     * @param landId 土地id
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult getLandInfo(@RequestParam("landId") String landId) {
        return landService.getLandInfo(landId);
    }

    /**
     * 获取land源数据
     *
     * @param landId
     * @return
     */
    @PostMapping("/info")
    public ResponseResult getLandById(@RequestParam("landId") String landId) {
        return landService.getLandById(landId);
    }

    /**
     * 更改土地状态
     *
     * @param status
     * @return
     */
    @PostMapping("/status")
    public ResponseResult updateLandStatus(@RequestParam("landId") String landId,
                                           @RequestParam("status") Integer status) {
        return landService.updateLandStatus(landId, status);
    }


    /**
     * 修改土地信息
     *
     * @param land
     * @return
     */
    @PutMapping
    public ResponseResult updateLand(@RequestBody Land land) {
        return landService.updateLand(land);
    }

    /**
     * 根据土地id删除土地的信息
     *
     * @param landId
     * @return
     */
    @DeleteMapping
    public ResponseResult delLandById(String landId) {
        return landService.delLandById(landId);
    }

    /**
     * 分页查询土地
     *
     * @param index
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<MyPage<List<LandVO>>> listLand(
            @RequestParam(value = "index", defaultValue = "1") Integer index,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("userId") Integer userId,
            @RequestParam(value = "status", required = false) Integer status) {
        return landService.listLand(index, pageSize, userId, status);
    }

    /**
     * 根据土地名字模糊搜索土地
     *
     * @param index
     * @param pageSize
     * @param landName
     * @return
     */
    @PostMapping("/search")
    public ResponseResult searchLandByLandName(@RequestParam(value = "index", defaultValue = "1") Integer index,
                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                               @RequestParam("landName") String landName) {
        return landService.searchLandByLandName(index, pageSize, landName);
    }

}

