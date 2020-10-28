package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandManageMode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 土地管理模式表 服务类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
public interface ILandManageModeService extends IService<LandManageMode> {

    /**
     *
     * @param landManageMode
     * @return
     */
    ResponseResult saveLandManageMode(LandManageMode landManageMode);

    /**
     * 获取土地管理模式列表
     *
     * @return
     */
    ResponseResult listLandManageMode();

    /**
     * 删除土地管理模式
     *
     * @param landManageModeId
     * @return
     */
    ResponseResult deleteLandManageMode(Integer landManageModeId);
}
