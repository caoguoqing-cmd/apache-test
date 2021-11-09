package com.example.apache.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 复合消费确认入参
 */
@Data
public class ConsumeConfirmDto implements Serializable {
    private static final long serialVersionUID = 6606919368588238616L;

    /**
     * 业务系统识别号
     */
    private String clientId;

    /**
     * 省级业务流水号
     */
    private String proListNo;

    /**
     * 交易后余额
     */
    private String balance;

    /**
     * 交易后脱机序列号
     */
    private String offlineSerialAfter;

    /**
     * MAC2
     */
    private String mac2;

    /**
     * TAC码
     */
    private String tac;

    /**
     * 结果码
     */
    private String code;

    /**
     * 校验值
     */
    private String tranMac;
}
