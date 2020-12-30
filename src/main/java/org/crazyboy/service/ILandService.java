package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.Land;
import com.baomidou.mybatisplus.extension.service.IService;
import org.crazyboy.model.MyPage;
import org.crazyboy.vo.LandVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 土地表 服务类
 * </p>
 *
 * @author Kevin
 * @since 2020-10-13
 */
public interface ILandService extends IService<Land> {
    /**
     * 添加土地
     *
     * @param land land
     * @return
     */
    ResponseResult saveLand(Land land);

    /**
     * 上传土地封面到七牛云OSS
     *
     * @param multipartFile 封面图片文件
     * @param landId        土地id
     * @return
     */
    ResponseResult uploadLandCoverImg(MultipartFile multipartFile, String landId) throws IOException;

    /**
     * 根据土地信息查询土地id
     *
     * @param landId
     * @return
     */
    ResponseResult getLandInfo(String landId);

    /**
     * 根据土地id删除土地信息
     *
     * @param landId
     * @return
     */
    ResponseResult delLandById(String landId);

    /**
     * 分页查询土地
     *
     * @param index
     * @param pageSize
     * @param userId
     * @param status
     * @return
     */
    ResponseResult<MyPage<List<LandVO>>> listLand(Integer index, Integer pageSize, Integer userId, Integer status);

    /**
     * 修改土地
     *
     * @param land
     * @return
     */
    ResponseResult updateLand(Land land);

    /**
     * 根据土地名称模糊查询
     *
     * @param index
     * @param pageSize
     * @param landName
     * @return
     */
    ResponseResult searchLandByLandName(Integer index, Integer pageSize, String landName);

    /**
     * 修改土地状态
     *
     * @param landId
     * @param status
     * @return
     */
    ResponseResult updateLandStatus(String landId, Integer status);

    /**
     * 获取所有土地列表
     *
     * @param status
     * @return
     */
    ResponseResult listAllLand(Integer status);

    /**
     *
     * @param landId
     * @return
     */
    ResponseResult getLandById(String landId);
}
