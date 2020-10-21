package org.crazyboy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: kevin
 * @date: 2020/9/26
 * @time: 上午1:38
 * @description: 土地状态枚举
 **/
@Getter
@AllArgsConstructor
public enum LandStatus {

    /**
     * 土地状态
     */
    UNPUBLISHED,
    PUBLISHED,
    FINISHED,
    ;
}
