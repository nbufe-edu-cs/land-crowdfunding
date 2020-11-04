package org.crazyboy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 土地种植农场品类表
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LandProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 农产品名称
     */
    private String productName;

    /**
     * 土地id
     */
    private String landId;

    /**
     * 单价
     */
    private String price;

    /**
     * 最小数量
     */
    private Integer minNum;

    /**
     * 最大数量
     */
    private Integer maxNum;

    /**
     * 推荐数量
     */
    private Integer recommendNum;

    /**
     * 农产品id
     */
    private String productId;

}
