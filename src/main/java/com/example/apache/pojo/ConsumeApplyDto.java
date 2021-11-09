package com.example.apache.pojo;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 复合消费申请入参
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ConsumeApplyDto implements Serializable {
    private static final long serialVersionUID = 6606919368588238616L;

    /**
     * 业务系统识别号
     */
    @NotNull
    private String clientId;

    /**
     * 省级业务流水号
     */
    @NotNull
    private String proListNo;

    /**
     * 密钥版本号
     */
    @NotNull
    private String keyVersion;

    /**
     * 卡号
     */
    @NotNull
    private String cardNo;

    /**
     * 省份编码
     */
    @NotNull
    private String provinceCode;

    /**
     * 脱机序列号
     */
    @NotNull
    private String offlineSerial;

    /**
     * 交易金额
     */
    @NotNull
    private String money;

    /**
     * 余额
     */
    @NotNull
    private String balance;

    /**
     * 密钥版本
     */
    @NotNull
    private String version;

    /**
     * 算法标识
     */
    @NotNull
    private String algorithm;

    /**
     * 伪随机数
     */
    @NotNull
    private String rand;

    /**
     * 交易日期
     */
    @NotNull
    private String tradeDate;

    /**
     * 交易时间
     */
    @NotNull
    private String tradeTime;

    /**
     * 密钥标识
     */
    @NotNull
    private String keyId;

    /**
     * 终端机编号
     */
    @NotNull
    private String terminalNo;

    /**
     * 业务类型
     */
    @NotNull
    private String type;

    /**
     * 校验值
     */
    @NotNull
    private String tranMac;
}
