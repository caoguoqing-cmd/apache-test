package com.example.apache.pojo;

import lombok.Data;


/**
 * @author leiChengxin
 * @version 1.0
 * @date 2020/11/18
 * @description PSAM卡授权请求参数
 */
@Data
public class AuthorizeApplyDto {

    private static final long serialVersionUID = -8830649779624923663L;
    /**
     * 业务系统识别号
     */
    private String clientId;

    /**
     * 省级业务流水号
     */
    private String proListNo;

    /**
     * PSAM卡序列号
     */
    private String psamNo;

    /**
     * 密钥版本号
     */
    private String keyVersion;

    /**
     * 随机数
     */
    private String random;

    /**
     * 省域编码
     */
    private String provinceCode;

    /**
     * 路段编码
     */
    private String roadCode;

    /**
     * 路段名称
     */
    private String roadName;

    /**
     * 收费站编码
     */
    private String stationCode;

    /**
     * 收费站名称
     */
    private String stationName;

    /**
     * 收费站类型
     */
    private String stationType;

    /**
     * 车道号
     */
    private String laneNo;

    /**
     * 车道类型
     */
    private String laneType;

    /**
     * 终端时间
     */
    private String terminalTime;

    /**
     * 校验值
     */
    private String tranMac;
}
