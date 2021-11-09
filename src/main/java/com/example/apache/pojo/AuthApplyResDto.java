package com.example.apache.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangKai
 * @version 1.0
 * @date 2020/11/23
 * @description 授权申请结果dto
 */
@Data
@AllArgsConstructor
public class AuthApplyResDto {

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应码
     */
    private String code;

    /**
     * 中心业务流水号
     */
    private String listNo;

    /**
     * 认证指令
     */
    private String authInstruction;

    /**
     * 省级业务单据号
     */
    private String proListNo;
}
