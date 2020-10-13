package org.crazyboy.controller;


import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.LandCrowdfunding;
import org.crazyboy.service.ILandCrowdfundingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 土地众筹信息表 前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/crowdfunding")
public class LandCrowdfundingController {

    @Resource
    private ILandCrowdfundingService landCrowdfundingService;

    /**
     * 参加众筹
     *
     * @param landCrowdfunding
     * @return
     */
    @PostMapping
    public ResponseResult participateInCrowdfunding(@RequestBody LandCrowdfunding landCrowdfunding) {
        return landCrowdfundingService.participateInCrowdfunding(landCrowdfunding);
    }
}

