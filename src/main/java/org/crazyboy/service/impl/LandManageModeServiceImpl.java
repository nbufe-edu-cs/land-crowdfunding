package org.crazyboy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandManageMode;
import org.crazyboy.mapper.LandManageModeMapper;
import org.crazyboy.service.ILandManageModeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
                .eq(LandManageMode::getUserId, landManageMode.getUserId()));
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
     * @return
     */
    @Override
    public ResponseResult listLandManageMode() {
        return ResponseResult.success("OK", list());
    }

    /**
     * 删除土地管理模式
     *
     * @param landManageModeId
     * @return
     */
    @Override
    public ResponseResult deleteLandManageMode(Integer landManageModeId) {
        LandManageMode landManageMode = getById(landManageModeId);
        if (ObjectUtil.isEmpty(landManageMode)) {
            return ResponseResult.error(ResponseCode.L_M_M_NOT_EXIST.getCode(), ResponseCode.L_M_M_NOT_EXIST.getMsg());
        }
        if (removeById(landManageModeId)) {
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_M_M_DEL_ERR.getCode(), ResponseCode.L_M_M_DEL_ERR.getMsg());
    }
}