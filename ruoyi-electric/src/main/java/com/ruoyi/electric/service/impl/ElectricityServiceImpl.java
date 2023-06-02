package com.ruoyi.electric.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.electric.domain.vo.LineChartVo;
import com.ruoyi.electric.tools.Tools;
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

    @Override
    public List<Integer> getDormIdList() {
        return electricityMapper.selectDormIds();
    }

    @Override
    public LineChartVo selectElectricityListByDate(String start, String end, Integer dormId) {
        if (dormId == null
                || start == null
                || end == null
                || start.isEmpty()
                || end.isEmpty()
                || !Tools.isDate(start)
                || !Tools.isDate(end)
        ) {
            return null;
        }

        List<Electricity> electricities = electricityMapper.selectElectricityListByDate(start, end, dormId);

        LineChartVo lineChartVo = new LineChartVo();
        List<String> date = electricities.stream().map(Electricity::getDate).collect(Collectors.toList());
        List<Double> airUsage = electricities.stream().map(Electricity::getAirElectricityUsage).collect(Collectors.toList());
        List<Double> lightningUsage = electricities.stream().map(Electricity::getLightingElectricityUsage).collect(Collectors.toList());

        lineChartVo.setDate(date);
        lineChartVo.setAirElectricityUsageData(airUsage);
        lineChartVo.setLightingElectricityUsageData(lightningUsage);

        return lineChartVo;
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
