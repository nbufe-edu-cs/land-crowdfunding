package org.crazyboy.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.crazyboy.common.constants.Qiniu;
import org.crazyboy.common.enums.LandStatus;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.Land;
import org.crazyboy.mapper.LandMapper;
import org.crazyboy.model.MyPage;
import org.crazyboy.service.ILandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.crazyboy.utils.qiniu.OSSUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 土地表 服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-13
 */
@Slf4j
@Service
public class LandServiceImpl extends ServiceImpl<LandMapper, Land> implements ILandService {

    @Resource
    private LandMapper landMapper;

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

    /**
     * 根据土地id删除土地信息
     *
     * @param landId 土地id
     * @return
     */
    @Override
    public ResponseResult delLandById(String landId) {
        Land land = (Land) getLandById(landId).getData();
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_NOT_EXIST.getMsg());
        }
        boolean isRemoved = remove(new LambdaQueryWrapper<Land>().eq(Land::getLandId, land));
        if (isRemoved) {
            return ResponseResult.success("OK");
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 分页查询土地
     *
     * @param index
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult<MyPage<List<Land>>> listLand(Integer index, Integer pageSize,
                                                       Integer userId, Integer status) {
        Page<Land> page = new Page<>(index, pageSize);
        QueryWrapper<Land> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtil.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        queryWrapper.eq("user_id", userId).orderByDesc("update_time");
        IPage<Land> iPage = landMapper.selectPage(page, queryWrapper);
        MyPage<List<Land>> result = new MyPage<>(iPage.getRecords(), index, iPage.getPages(), iPage.getTotal());
        return ResponseResult.success("OK", result);
    }

    /**
     * 修改土地
     *
     * @param land
     * @return
     */
    @Override
    public ResponseResult updateLand(Land land) {
        land.setUpdateTime(LocalDateTime.now());
        if (updateById(land)) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_UPDATE_ERR.getCode(), ResponseCode.L_UPDATE_ERR.getMsg());
    }

    /**
     * 根据土地名字模糊搜索土地
     *
     * @param index
     * @param pageSize
     * @param landName
     * @return
     */
    @Override
    public ResponseResult<MyPage<List<Land>>> searchLandByLandName(Integer index, Integer pageSize, String landName) {
//        Page<Land> page = new Page<>(index, pageSize);
//        IPage<Land> iPage = landMapper.selectPage(page, new QueryWrapper<Land>().like("land_name", landName));
//        MyPage<List<Land>> myPage = new MyPage<>(iPage.getRecords(), index, iPage.getPages(), iPage.getTotal());
//        return ResponseResult.success("OK", myPage);
        return null;
    }


}
