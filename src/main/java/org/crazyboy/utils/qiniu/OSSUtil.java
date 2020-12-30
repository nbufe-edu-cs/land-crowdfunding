package org.crazyboy.utils.qiniu;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.crazyboy.common.constants.Qiniu;
import org.crazyboy.model.QiniuPutRet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author: kevin
 * @date: 2020/9/26
 * @time: 上午12:08
 * @description:
 **/

@Slf4j
@Component
public class OSSUtil {

    /**
     * upload object to Qiniu OSS
     *
     * @param file Object
     * @return final file name
     * @throws IOException
     */
    public String uploadObject(MultipartFile file) throws IOException {
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            byte[] uploadBytes = file.getBytes();
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
            try {
                Response response = uploadManager.put(
                        byteInputStream, getFileKey(file), getToken(), null, null);
                //analysis result for upload successfully
                QiniuPutRet putRet = JSONUtil.toBean(response.bodyString(), QiniuPutRet.class);
                log.info("putRet key ----> " + putRet.key);
                log.info("putRet hash ----> " + putRet.hash);
                return putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }
        return null;
    }

    /**
     * delete object from Qiniu OSS
     *
     * @param key key
     */
    public void deleteObject(String key) {
        Configuration cfg = new Configuration(Region.region0());
        Auth auth = Auth.create(Qiniu.ACCESS_KEY, Qiniu.SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(Qiniu.BUCKET_NAME, key);
        } catch (QiniuException ex) {
            log.error("oss delete error code ----> " + ex.code());
            log.error("oss delete error response ----> " + ex.response.toString());
        }
    }

    /**
     * get qiniu token
     *
     * @return token
     */
    public String getToken() {
        Auth auth = Auth.create(Qiniu.ACCESS_KEY, Qiniu.SECRET_KEY);
        return auth.uploadToken(Qiniu.BUCKET_NAME);
    }

    /**
     * rename file
     *
     * @param file MultipartFile
     * @return a new name for this MultipartFile
     */
    private String getFileKey(MultipartFile file) {
        String name = file.getOriginalFilename();
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return name;
        } else {
            String fileName = IdUtil.fastSimpleUUID();
            String fileSuffix = name.substring(index + 1);
            return fileName + "." + fileSuffix;
        }
    }

}
