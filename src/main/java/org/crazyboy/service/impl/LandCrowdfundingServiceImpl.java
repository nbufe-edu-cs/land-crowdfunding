package org.crazyboy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.crazyboy.common.response.ResponseCode;
import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.Land;
import org.crazyboy.entity.LandCrowdfunding;
import org.crazyboy.mapper.LandCrowdfundingMapper;
import org.crazyboy.mapper.LandMapper;
import org.crazyboy.service.ILandCrowdfundingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.crazyboy.service.ILandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * <p>
 * 土地众筹信息表 服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@Service
public class LandCrowdfundingServiceImpl extends ServiceImpl<LandCrowdfundingMapper, LandCrowdfunding>
        implements ILandCrowdfundingService {

    @Resource
    private LandCrowdfundingMapper landCrowdfundingMapper;

    @Resource
    private ILandService landService;

    /**
     * 参加众筹
     *
     * @param landCrowdfunding
     * @return
     */
    @Override
    public ResponseResult participateInCrowdfunding(LandCrowdfunding landCrowdfunding) {
        Land land = (Land) landService.getLandById(landCrowdfunding.getLandId()).getData();
        if (ObjectUtil.isEmpty(land)) {
            return ResponseResult.error(ResponseCode.L_NOT_EXIST);
        }
        float share = Float.parseFloat(landCrowdfunding.getShare());
        float targetAmount = Float.parseFloat(land.getTargetAmount());
        float percent = share / targetAmount;
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        landCrowdfunding.setPercent(decimalFormat.format(percent));
        landCrowdfunding.setCreateTime(LocalDateTime.now());
        // 更新土地信息
        if (1 == landCrowdfundingMapper.insert(landCrowdfunding)) {
            float currentAmount = Float.parseFloat(land.getCurrentAmount()) + share;
            float progress = currentAmount / targetAmount;
            land.setCurrentAmount(decimalFormat.format(currentAmount));
            land.setProgress(decimalFormat.format(progress));
            landService.updateLand(land);
            return ResponseResult.success("OK");
        }
        return ResponseResult.error(ResponseCode.L_C_PARTICIPATE_ERR);
    }
}
