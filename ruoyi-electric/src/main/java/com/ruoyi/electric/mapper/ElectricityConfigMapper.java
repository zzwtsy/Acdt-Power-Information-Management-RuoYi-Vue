package com.ruoyi.electric.mapper;

import com.ruoyi.electric.domain.ElectricityConfig;
import org.springframework.stereotype.Repository;

/**
 * 宿舍电费信息的相关配置Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-08
 */
@Repository
public interface ElectricityConfigMapper {
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
     * 删除宿舍电费信息的相关配置
     *
     * @param cookie 宿舍电费信息的相关配置主键
     * @return 结果
     */
    public int deleteElectricityConfigById(String cookie);

    /**
     * 批量删除宿舍电费信息的相关配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElectricityConfigByIds(String[] ids);
}
