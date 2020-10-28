package org.crazyboy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.crazyboy.entity.LandProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 土地种植农场品类表 Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2020-10-28
 */
@Mapper
public interface LandProductMapper extends BaseMapper<LandProduct> {

}
