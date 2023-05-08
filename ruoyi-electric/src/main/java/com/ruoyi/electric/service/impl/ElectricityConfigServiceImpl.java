package com.ruoyi.electric.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.electric.mapper.ElectricityConfigMapper;
import com.ruoyi.electric.domain.ElectricityConfig;
import com.ruoyi.electric.service.IElectricityConfigService;

import javax.annotation.Resource;

/**
 * 宿舍电费信息的相关配置Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-08
 */
@Service
public class ElectricityConfigServiceImpl implements IElectricityConfigService {
    @Autowired
    private ElectricityConfigMapper electricityConfigMapper;

    /**
     * 查询宿舍电费信息的相关配置
     *
     * @return 宿舍电费信息的相关配置
     */
    @Override
    public ElectricityConfig selectElectricityConfig() {
        return electricityConfigMapper.selectElectricityConfig();
    }

    /**
     * 新增宿舍电费信息的相关配置
     *
     * @param electricityConfig 宿舍电费信息的相关配置
     * @return 结果
     */
    @Override
    public int insertElectricityConfig(ElectricityConfig electricityConfig) {
        return electricityConfigMapper.insertElectricityConfig(electricityConfig);
    }

    /**
     * 修改宿舍电费信息的相关配置
     *
     * @param electricityConfig 宿舍电费信息的相关配置
     * @return 结果
     */
    @Override
    public int updateElectricityConfig(ElectricityConfig electricityConfig) {
        return electricityConfigMapper.updateElectricityConfig(electricityConfig);
    }

    /**
     * 批量删除宿舍电费信息的相关配置
     *
     * @param ids 需要删除的宿舍电费信息的相关配置主键
     * @return 结果
     */
    @Override
    public int deleteElectricityConfigByCookies(String[] ids) {
        return electricityConfigMapper.deleteElectricityConfigByIds(ids);
    }

    /**
     * 删除宿舍电费信息的相关配置信息
     *
     * @param id 宿舍电费信息的相关配置主键
     * @return 结果
     */
    @Override
    public int deleteElectricityConfigByCookie(String id) {
        return electricityConfigMapper.deleteElectricityConfigById(id);
    }
}
