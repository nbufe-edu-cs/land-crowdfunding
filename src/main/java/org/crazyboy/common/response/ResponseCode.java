package org.crazyboy.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: kevin
 * @date: 2020/9/25
 * @time: 下午10:03
 * @description: Server response code
 **/

@AllArgsConstructor
@Getter
public enum ResponseCode {

    /**
     * server response code
     */
    SUCCESS(200, "success"),
    ERROR(0, "error"),
    /**
     * qiniu cloud OSS error code
     */
    Q_OSS_UPLOAD_ERR(1, "文件上传失败"),
    /**
     * land
     */
    L_SAVE_ERR(101, "土地信息保存失败"),
    L_NOT_EXIST(102, "土地不存在"),
    L_UPDATE_ERR(103, "土地信息更新失败"),
    /**
     * land crowdfunding
     */
    L_C_PARTICIPATE_ERR(201, "参与众筹失败");

    public int code;
    public String msg;
}
