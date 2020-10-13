package org.crazyboy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 土地众筹信息表
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LandCrowdfunding implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 土地id
     */
    private String landId;

    /**
     * 参与者id
     */
    private Integer partnerId;

    /**
     * 参与份额
     */
    private String share;

    /**
     * 百分比
     */
    private String percent;

    /**
     * 参与时间
     */
    private LocalDateTime createTime;


}
