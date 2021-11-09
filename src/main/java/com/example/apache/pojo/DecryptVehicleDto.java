package com.example.apache.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 解密车辆信息入参
 */
@Data
public class DecryptVehicleDto implements Serializable {
    private static final long serialVersionUID = 6606919368588238616L;

    /**
     * 业务系统识别号
     */
    private String clientId;

    /**
     * OBU合同序列号
     */
    private String obuSerial;

    /**
     * 密钥版本号
     */
    private String keyVersion;

    /**
     * 密文数据
     */
    private String data;

    /**
     * 密钥版本
     */
    private String version;

    /**
     * 省域编码
     */
    private String provinceCode;

    /**
     * 业务类型  00-客服业务
     * 01-停车场业务
     * 02-加油站业务
     */
    private String type;

    /**
     * 校验值
     */
    private String tranMac;
}
