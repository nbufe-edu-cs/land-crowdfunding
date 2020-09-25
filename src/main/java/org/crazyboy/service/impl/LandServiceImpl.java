package org.crazyboy.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.crazyboy.common.constants.Qiniu;
import org.crazyboy.common.enums.LandStatus;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.Land;
import org.crazyboy.mapper.LandMapper;
import org.crazyboy.service.ILandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.crazyboy.utils.qiniu.OSSUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 土地表 服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@Slf4j
@Service
public class LandServiceImpl extends ServiceImpl<LandMapper, Land> implements ILandService {

    @Resource
    private OSSUtil ossUtil;

    /**
     * 添加土地
     *
     * @param land land
     * @return
     */
    @Override
    public ResponseResult saveLand(Land land) {
        land.setLandId(IdUtil.simpleUUID());
        land.setStatus(LandStatus.UNPUBLISHED.getStatus());
        land.setCurrentAmount(String.valueOf(0));
        land.setProgress(String.valueOf(0));
        land.setCreateTime(LocalDateTime.now());
        land.setUpdateTime(LocalDateTime.now());
        if (save(land)) {
            Map<String, Object> result = new HashMap<>();
            result.put("landId", land.getLandId());
            return ResponseResult.success("Saved land information successfully", result);
        } else {
            return ResponseResult.error(ResponseCode.L_SAVE_ERR.getCode(), ResponseCode.L_SAVE_ERR.getMsg());
        }
    }

    /**
     * 上传土地封面到七牛云OSS
     *
     * @param multipartFile 封面图片文件
     * @return
     */
    @Override
    public ResponseResult uploadLandCoverImg(MultipartFile multipartFile, String landId) throws IOException {
        String fileName = ossUtil.uploadObject(multipartFile);
        if (ObjectUtil.isEmpty(fileName)) {
            return ResponseResult.error(
                    ResponseCode.Q_OSS_UPLOAD_ERR.getCode(), ResponseCode.Q_OSS_UPLOAD_ERR.getMsg());
        }
        String coveImgUrl = Qiniu.OSS_DEFAULT_DOMAIN + fileName;
        log.info("save cover image successfully");
        log.info("image cdn xddress ------> " + coveImgUrl);
        Land land = getOne(new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId));
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_SAVE_ERR.getMsg());
        }
        land.setCoverImg(coveImgUrl);
        if (update(land, new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId))) {
            return ResponseResult.success("Saved cover image successful", coveImgUrl);
        } else {
            ossUtil.deleteObject(fileName);
            log.info("delete cover image " + coveImgUrl + " from qiniu OSS");
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_SAVE_ERR.getMsg());
        }
    }

    /**
     * 根据土地信息查询土地id
     *
     * @param landId 土地id
     * @return
     */
    @Override
    public ResponseResult getLandById(String landId) {
        Land land = getOne(new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId));
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_NOT_EXIST.getMsg());
        }
        return ResponseResult.success("OK", land);
    }
}
