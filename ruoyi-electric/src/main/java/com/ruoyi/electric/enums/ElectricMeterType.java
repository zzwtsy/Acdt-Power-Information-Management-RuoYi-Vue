package com.ruoyi.electric.enums;

/**
 * 电表类型
 *
 * @author zzwtsy
 * @since 2023/04/21
 */
public enum ElectricMeterType {
    /**
     * 空调
     */
    AIR("空调"),
    /**
     * 照明
     */
    LIGHTING("照明");

    private final String typeName;

    ElectricMeterType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
