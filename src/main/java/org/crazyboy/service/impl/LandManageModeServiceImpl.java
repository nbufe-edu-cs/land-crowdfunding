package org.crazyboy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandManageMode;
import org.crazyboy.mapper.LandManageModeMapper;
import org.crazyboy.service.ILandManageModeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 土地管理模式表 服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Service
public class LandManageModeServiceImpl extends ServiceImpl<LandManageModeMapper, LandManageMode> implements ILandManageModeService {

    /**
     * @param landManageMode
     * @return
     */
    @Override
    public ResponseResult saveLandManageMode(LandManageMode landManageMode) {
        LandManageMode manageMode = getOne(new LambdaQueryWrapper<LandManageMode>()
                .eq(LandManageMode::getModeName, landManageMode.getModeName())
                .eq(LandManageMode::getLandId, landManageMode.getLandId()));
        if (!ObjectUtil.isEmpty(manageMode)) {
            return ResponseResult.error(ResponseCode.L_M_M_EXIST.getCode(), ResponseCode.L_M_M_EXIST.getMsg());
        }
        if (save(landManageMode)) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_M_M_SAVE_ERR.getCode(), ResponseCode.L_M_M_SAVE_ERR.getMsg());
    }

    /**
     * 获取土地管理模式列表
     *
     * @param landId
     * @return
     */
    @Override
    public ResponseResult getLandManageMode(String landId) {
        return ResponseResult.success("OK", getOne(new LambdaQueryWrapper<LandManageMode>()
                .eq(LandManageMode::getLandId, landId)));
    }

    /**
     * 删除土地管理模式
     *
     * @param landId
     * @param modeName
     * @return
     */
    @Override
    public ResponseResult deleteLandManageMode(String landId, String modeName) {
        LandManageMode landManageMode = getOne(new LambdaQueryWrapper<LandManageMode>()
                .eq(LandManageMode::getLandId, landId)
                .eq(LandManageMode::getModeName, modeName));
        if (ObjectUtil.isEmpty(landManageMode)) {
            return ResponseResult.error(ResponseCode.L_M_M_NOT_EXIST.getCode(), ResponseCode.L_M_M_NOT_EXIST.getMsg());
        }
        if (remove(new LambdaQueryWrapper<LandManageMode>()
                .eq(LandManageMode::getLandId, landId)
                .eq(LandManageMode::getModeName, modeName))) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_M_M_DEL_ERR.getCode(), ResponseCode.L_M_M_DEL_ERR.getMsg());
    }
}
