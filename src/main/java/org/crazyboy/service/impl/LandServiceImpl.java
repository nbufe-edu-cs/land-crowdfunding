package org.crazyboy.service.impl;

import cn.hutool.core.collection.CollectionUtil;
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
import org.crazyboy.entity.LandPlantFood;
import org.crazyboy.entity.LandProduct;
import org.crazyboy.mapper.LandMapper;
import org.crazyboy.model.MyPage;
import org.crazyboy.service.ILandPlantFoodService;
import org.crazyboy.service.ILandProductService;
import org.crazyboy.service.ILandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.crazyboy.utils.CommonUtils;
import org.crazyboy.utils.qiniu.OSSUtil;
import org.crazyboy.vo.LandVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
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

    @Resource
    private CommonUtils commonUtils;

    @Resource
    private ILandProductService productService;

    @Resource
    private ILandPlantFoodService plantFoodService;

    /**
     * 添加土地
     *
     * @param land land
     * @return
     */
    @Override
    public ResponseResult saveLand(Land land) {
        land.setLandId(IdUtil.simpleUUID());
        land.setUserId(1);
        land.setStatus(LandStatus.UNPUBLISHED.ordinal());
        land.setCurrentAmount(String.valueOf(0));
        land.setProgress(String.valueOf(0));
        land.setCreateTime(commonUtils.getCurrentTimestamp());
        land.setUpdateTime(commonUtils.getCurrentTimestamp());
        if (save(land)) {
            Map<String, Object> result = new HashMap<>();
            result.put("landId", land.getLandId());
            return ResponseResult.success("OK", result);
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
        log.info("SAVE COVER IMAGE SUCCESS");
        log.info("IMAGE CDN ADDRESS ------> " + coveImgUrl);
        Land land = getOne(new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId));
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_SAVE_ERR.getMsg());
        }
        land.setCoverImg(coveImgUrl);
        if (update(land, new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId))) {
            return ResponseResult.success("OK", coveImgUrl);
        } else {
            ossUtil.deleteObject(fileName);
            log.info("DELETE COVER IMAGE " + coveImgUrl + " FROM QINIU OSS");
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_SAVE_ERR.getMsg());
        }
    }

    /**
     * Get land information by land id
     *
     * @param landId land id
     * @return
     */
    @Override
    public ResponseResult<Map<String, Object>> getLandInfo(String landId) {
        Map<String, Object> result = new HashMap<>();
        Land land = getOne(new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId));
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_NOT_EXIST.getMsg());
        }
        result.put("land", voLand(land));
        List<LandProduct> landProductList = productService.listLandProduct(landId).getData();
        List<Map<String, Object>> productList = new ArrayList<>();
        landProductList.forEach(item -> {
            Map<String, Object> productWithPlantFood = new HashMap<>();
            productWithPlantFood.put("product", item);
            List<LandPlantFood> landPlantFoods =
                    plantFoodService.getLandPlantFoodByProduct(item.getProductId()).getData();
            if (!CollectionUtil.isEmpty(landPlantFoods)) {
                productWithPlantFood.put("plantFoodList", landPlantFoods);
            }
            productList.add(productWithPlantFood);
        });
        result.put("options", productList);
        return ResponseResult.success("OK", result);
    }


    /**
     * vo land object
     *
     * @param land
     * @return
     */
    private LandVO voLand(Land land) {
        LandVO landVO = new LandVO();
        landVO.setLandId(land.getLandId());
        landVO.setLandName(land.getLandName());
        landVO.setLandDesc(land.getLandDesc());
        landVO.setLandAddress(land.getLandAddress());
        landVO.setLandArea(land.getLandArea());
        landVO.setTargetAmount(land.getTargetAmount());
        landVO.setCurrentAmount(land.getCurrentAmount());
        landVO.setProgress(land.getProgress());
        landVO.setCoverImg(land.getCoverImg());
        landVO.setStatus(land.getStatus());
        landVO.setUserId(land.getUserId());
        landVO.setStartDate(commonUtils.formatTimestamp2Date(land.getStartDate()));
        landVO.setEndDate(commonUtils.formatTimestamp2Date(land.getEndDate()));
        landVO.setCreateTime(commonUtils.formatTimestamp2Date(land.getCreateTime()));
        landVO.setUpdateTime(commonUtils.formatTimestamp2Date(land.getUpdateTime()));
        return landVO;
    }

    /**
     * 根据土地id删除土地信息
     *
     * @param landId 土地id
     * @return
     */
    @Override
    public ResponseResult delLandById(String landId) {
        Land land = getOne(new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId));
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_NOT_EXIST.getMsg());
        }
        if (land.getStatus().equals(LandStatus.PUBLISHED.ordinal())) {
            return ResponseResult.error(ResponseCode.L_PUBLISHING.getCode(), ResponseCode.L_PUBLISHING.getMsg());
        }
        boolean isRemoved = remove(new LambdaQueryWrapper<Land>().eq(Land::getLandId, land.getLandId()));
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
    public ResponseResult<MyPage<List<LandVO>>> listLand(Integer index, Integer pageSize,
                                                       Integer userId, Integer status) {
        Page<Land> page = new Page<>(index, pageSize);
        QueryWrapper<Land> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtil.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        queryWrapper.eq("user_id", userId).orderByDesc("update_time");
        IPage<Land> iPage = landMapper.selectPage(page, queryWrapper);
        List<LandVO> list = new ArrayList<>();
        iPage.getRecords().forEach(item -> {
            list.add(voLand(item));
        });
        MyPage<List<LandVO>> result = new MyPage<>(list, index, iPage.getPages(), iPage.getTotal());
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
        land.setUpdateTime(commonUtils.getCurrentTimeStamp());
        if (update(land, new LambdaQueryWrapper<Land>().eq(Land::getLandId, land.getLandId()))) {
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
        Page<Land> page = new Page<>(index, pageSize);
        IPage<Land> iPage = landMapper.selectPage(page, new QueryWrapper<Land>().like("land_name", landName));
        MyPage<List<Land>> myPage = new MyPage<>(iPage.getRecords(), index, iPage.getPages(), iPage.getTotal());
        return ResponseResult.success("OK", myPage);
    }

    /**
     * 修改土地状态
     *
     * @param status
     * @return
     */
    @Override
    public ResponseResult updateLandStatus(String landId, Integer status) {
        Land land = getOne(new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId));
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST.getCode(), ResponseCode.L_NOT_EXIST.getMsg());
        }
        if (status.equals(LandStatus.FINISHED.ordinal())) {
            if (Integer.parseInt(land.getCurrentAmount()) < Integer.parseInt(land.getTargetAmount())) {
                return ResponseResult.error(
                        ResponseCode.L_NOT_REACHED.getCode(), ResponseCode.L_NOT_REACHED.getMsg());
            }
        }
        land.setStatus(status);
        if (update(land, new LambdaQueryWrapper<Land>().eq(Land::getLandId, landId))) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_UPDATE_ERR.getCode(), ResponseCode.L_UPDATE_ERR.getMsg());
    }

    /**
     * 获取所有土地列表
     *
     * @param status
     * @return
     */
    @Override
    public ResponseResult<List<LandVO>> listAllLand(Integer status) {
        LambdaQueryWrapper<Land> queryWrapper = new LambdaQueryWrapper<>();
        if (!ObjectUtil.isEmpty(status)) {
            queryWrapper.eq(Land::getStatus, status);
        }
        List<Land> landList = list(queryWrapper);
        List<LandVO> result = new ArrayList<>();
        landList.forEach(item -> {
            result.add(voLand(item));
        });
        return ResponseResult.success("OK", result);
    }

    /**
     * @param landId
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