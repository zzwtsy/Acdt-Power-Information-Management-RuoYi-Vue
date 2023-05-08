package com.ruoyi.electric.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.electric.mapper.ElectricityMapper;
import com.ruoyi.electric.domain.Electricity;
import com.ruoyi.electric.service.IElectricityService;

/**
 * 宿舍电费记录Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-04
 */
@Service
public class ElectricityServiceImpl implements IElectricityService {
    @Autowired
    private ElectricityMapper electricityMapper;

    /**
     * 查询宿舍电费记录
     *
     * @param id 宿舍电费记录主键
     * @return 宿舍电费记录
     */
    @Override
    public Electricity selectElectricityById(Long id) {
        return electricityMapper.selectElectricityById(id);
    }

    /**
     * 查询宿舍电费记录列表
     *
     * @param electricity 宿舍电费记录
     * @return 宿舍电费记录
     */
    @Override
    public List<Electricity> selectElectricityList(Electricity electricity) {
        return electricityMapper.selectElectricityList(electricity);
    }

    /**
     * 新增宿舍电费记录
     *
     * @param electricity 宿舍电费记录
     * @return 结果
     */
    @Override
    public int insertElectricity(Electricity electricity) {
        electricity.setCreateTime(DateUtils.getNowDate());
        return electricityMapper.insertElectricity(electricity);
    }

    @Override
    public int insertElectricitys(List<Electricity> electricitys) {
        return electricityMapper.insertElectricitys(electricitys);
    }

    /**
     * 修改宿舍电费记录
     *
     * @param electricity 宿舍电费记录
     * @return 结果
     */
    @Override
    public int updateElectricity(Electricity electricity) {
        electricity.setUpdateTime(DateUtils.getNowDate());
        return electricityMapper.updateElectricity(electricity);
    }

    /**
     * 批量删除宿舍电费记录
     *
     * @param ids 需要删除的宿舍电费记录主键
     * @return 结果
     */
    @Override
    public int deleteElectricityByIds(Long[] ids) {
        return electricityMapper.deleteElectricityByIds(ids);
    }

    /**
     * 删除宿舍电费记录信息
     *
     * @param dormId 宿舍电费记录主键
     * @return 结果
     */
    @Override
    public int deleteElectricityByDormId(Long dormId) {
        return electricityMapper.deleteElectricityByDormId(dormId);
    }
}
