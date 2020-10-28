package org.crazyboy.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户土地收藏夹表
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LandFavorites implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 土地id
     */
    private String landId;


}
