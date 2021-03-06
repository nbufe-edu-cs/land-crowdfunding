package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandCrowdfunding;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 土地众筹信息表 服务类
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
public interface ILandCrowdfundingService extends IService<LandCrowdfunding> {

    /**
     * 参加众筹
     *
     * @param landCrowdfunding
     * @return
     */
    ResponseResult participateInCrowdfunding(LandCrowdfunding landCrowdfunding);
}
