package com.ruoyi.electric.service;

import java.util.List;

import com.ruoyi.electric.domain.ElectricityConfig;

/**
 * 宿舍电费信息的相关配置Service接口
 *
 * @author ruoyi
 * @since 2023-05-08
 */
public interface IElectricityConfigService {
    /**
     * 查询宿舍电费信息的相关配置
     *
     * @return 宿舍电费信息的相关配置
     */
    public ElectricityConfig selectElectricityConfig();


    /**
     * 新增宿舍电费信息的相关配置
     *
     * @param electricityConfig 宿舍电费信息的相关配置
     * @return 结果
     */
    public int insertElectricityConfig(ElectricityConfig electricityConfig);

    /**
     * 修改宿舍电费信息的相关配置
     *
     * @param electricityConfig 宿舍电费信息的相关配置
     * @return 结果
     */
    public int updateElectricityConfig(ElectricityConfig electricityConfig);

    /**
     * 批量删除宿舍电费信息的相关配置
     *
     * @param ids 需要删除的宿舍电费信息的相关配置主键集合
     * @return 结果
     */
    public int deleteElectricityConfigByCookies(String[] ids);

    /**
     * 删除宿舍电费信息的相关配置信息
     *
     * @param id 宿舍电费信息的相关配置主键
     * @return 结果
     */
    public int deleteElectricityConfigByCookie(String id);
}
