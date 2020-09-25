package org.crazyboy.model;

import lombok.Data;

/**
 * @author: kevin
 * @date: 2020/9/26
 * @time: 上午1:22
 * @description:
 **/

@Data
public class QiniuPutRet {
    public String key;
    public String hash;
    public String bucket;
    public long fsize;
}
