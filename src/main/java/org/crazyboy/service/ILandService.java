package org.crazyboy.service;

import org.crazyboy.common.response.ResponseResult;
import org.crazyboy.entity.Land;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 土地表 服务类
 * </p>
 *
 * @author Kevin
 * @since 2020-09-25
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
     * @param landId 土地id
     * @return
     */
    ResponseResult uploadLandCoverImg(MultipartFile multipartFile, String landId) throws IOException;

    /**
     * 根据土地信息查询土地id
     *
     * @param landId
     * @return
     */
    ResponseResult getLandById(String landId);
}
