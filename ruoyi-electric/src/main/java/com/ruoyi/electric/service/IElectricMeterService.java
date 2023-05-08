package com.ruoyi.electric.service;

import com.ruoyi.electric.domain.ElectricityBean;

import java.util.List;

public interface IElectricMeterService {

    /**
     * 获取第一条照明用电数据
     *
     * @param dormId 宿舍号
     * @return {@link List}<{@link ElectricityBean}>
     */
    ElectricityBean getLightingElectricData(String dormId);

    /**
     * 获取第一条空调用电数据
     *
     * @param dormId 宿舍号
     * @return {@link List}<{@link ElectricityBean}>
     */
    ElectricityBean getAirElectricData(String dormId);

    /**
     * 获取所有照明用电数据
     *
     * @return {@link List}<{@link ElectricityBean}>
     */
    List<ElectricityBean> getLightingElectricDataAll(String dormId);

    /**
     * 获取所有空调用电数据
     *
     * @return {@link List}<{@link ElectricityBean}>
     */
    List<ElectricityBean> getAirElectricDataAll(String dormId);
}
