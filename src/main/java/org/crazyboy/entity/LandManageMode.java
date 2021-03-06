package org.crazyboy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 土地管理模式表
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LandManageMode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理模式名称
     */
    private String modeName;

    /**
     * 土地id
     */
    private String landId;

    /**
     * 价格
     */
    private String price;


}
