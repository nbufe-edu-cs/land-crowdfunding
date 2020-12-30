package org.crazyboy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 土地表
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Land implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 土地id
     */
    private String landId;

    /**
     * 土地名称
     */
    private String landName;

    /**
     * 土地介绍
     */
    private String landDesc;

    /**
     * 土地地址
     */
    private String landAddress;

    /**
     * 土地面积
     */
    private String landArea;

    /**
     * 目标金额
     */
    private String targetAmount;

    /**
     * 当前金额
     */
    private String currentAmount;

    /**
     * 众筹进度
     */
    private String progress;

    /**
     * 土地封面图片
     */
    private String coverImg;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 土地所有者id、众筹发起人id
     */
    private Integer userId;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


}
