package org.crazyboy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 土地种植原料表
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LandPlantFood implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 化肥原料
     */
    private String plantFood;

    /**
     * 土地id
     */
    private String landId;

    /**
     * 单价
     */
    private String price;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 使用的农产品id
     */
    private String productId;

}
