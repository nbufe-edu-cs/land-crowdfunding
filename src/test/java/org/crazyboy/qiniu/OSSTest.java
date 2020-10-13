package org.crazyboy.qiniu;

import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.crazyboy.common.constants.Qiniu;
import org.crazyboy.utils.qiniu.OSSUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: kevin
 * @date: 2020/9/25
 * @time: 下午11:38
 * @description:
 **/

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OSSTest {

    @Resource
    private OSSUtil ossUtil;

    @Test
    public void qiniuOssTest() {
        Auth auth = Auth.create(Qiniu.ACCESS_KEY, Qiniu.SECRET_KEY);
        String token = auth.uploadToken(Qiniu.BUCKET_NAME);
        log.info("----- " + token + "-----");
    }

    @Test
    public void qiniuUploadObjectTest() throws IOException {
        File file = new File("/Users/kevin/Desktop/Kevin/竞赛/农业项目/land-crowdfunding/WechatIMG100.png");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
        ossUtil.uploadObject(multipartFile);
    }

    @Test
    public void qiniuDeleteObjectTest() {
        String key = "004531c8c9c54a188abba01b73c2f271.png";
        ossUtil.deleteObject(key);
    }
}
