package com.example.apache.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 张晖
 */
@Data @Accessors(chain = true)
public class DecryptVehicleInBlackDTO implements Serializable {

    private static final long serialVersionUID = -931341479388518808L;
    /**
     * 车牌
     */
    @JSONField(name = "plate_no")
    private String plateNo;

    /**
     * 车牌颜色
     */
    @JSONField(name = "plate_color")
    private String plateColor;

    /**
     * 车型
     */
    @JSONField(name = "car_type")
    private String carType;

    /**
     * 用户类型
     */
    @JSONField(name = "owner_type")
    private String ownerType;

    /**
     *车长 单位dm
     */
    private String length;

    /**
     * 车宽 单位dm
     */
    private String width;

    /**
     * 车高 单位dm
     */
    private String height;

    /**
     * 车轮数
     */
    @JSONField(name = "wheel_count")
    private String wheelCount;

    /**
     * 车轴数
     */
    @JSONField(name = "axle_count")
    private String axleCount;

    /**
     * 轴距
     */
    @JSONField(name = "axle_length")
    private String axleLength;

    /**
     * 车载重/车座位数
     */
    private String weight;

    /**
     * 车辆描述
     */
    private String desc;

    /**
     * 车辆发动机号
     */
    private String engine;

    /**
     * 是否是黑名单0-不是；1-是
     */
    @JSONField(name = "is_black")
    private String wasBlack;
}
