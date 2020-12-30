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
    L_SAVE_ERR(100, "土地信息保存失败"),
    L_NOT_EXIST(101, "土地不存在"),
    L_UPDATE_ERR(102, "土地信息更新失败"),
    L_PUBLISHING(103, "土地众筹进行中无法删除"),
    L_NOT_REACHED(104, "众筹未达目标金额，无法结束"),
    /**
     * land crowdfunding
     */
    L_C_PARTICIPATE_ERR(200, "参与众筹失败"),
    /**
     * land favorites
     */
    L_F_EXIST(300, "收藏夹内已存在"),
    L_F_NOT_EXIST(301, "该土地不再收藏夹内"),
    L_F_ADD_ERR(302, "添加收藏夹失败"),
    L_F_REMOVE_ERR(303, "移出收藏夹失败"),
    /**
     * land product
     */
    L_P_EXIST(400, "该农产品已存在"),
    L_P_NOT_EXIST(401, "该农产品不存在"),
    L_P_SAVE_ERR(402, "农产品保存失败"),
    L_P_DEL_ERR(403, "农产品删除失败"),
    /**
     * land manage mode
     */
    L_M_M_EXIST(500, "该管理模式已存在"),
    L_M_M_NOT_EXIST(501, "该管理模式不存在"),
    L_M_M_SAVE_ERR(502, "管理模式保存失败"),
    L_M_M_DEL_ERR(503, "管理模式删除失败"),
    /**
     * land plant food
     */
    L_P_F_EXIST(600, "该种植原料已存在"),
    L_P_F_NOT_EXIST(601, "该种植原料不存在"),
    L_P_F_SAVE_ERR(602, "种植原料保存失败"),
    L_P_F_DEL_ERR(603, "种植原料删除失败"),
    ;

    public int code;
    public String msg;
}
