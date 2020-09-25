package org.crazyboy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.crazyboy.entity.Land;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 土地表 Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
 */
@Mapper
public interface LandMapper extends BaseMapper<Land> {

}
