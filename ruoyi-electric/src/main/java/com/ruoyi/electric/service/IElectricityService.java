package com.ruoyi.electric.service;

import java.util.List;

import com.ruoyi.electric.domain.Electricity;
import com.ruoyi.electric.domain.vo.LineChartVo;

/**
 * 宿舍电费记录Service接口
 *
 * @author ruoyi
 * @date 2023-05-04
 */
public interface IElectricityService {
    /**
     * 查询宿舍电费记录
     *
     * @param id 宿舍电费记录主键
     * @return 宿舍电费记录
     */
    public Electricity selectElectricityById(Long id);

    /**
     * 查询宿舍电费记录列表
     *
     * @param electricity 宿舍电费记录
     * @return 宿舍电费记录集合
     */
    public List<Electricity> selectElectricityList(Electricity electricity);

    List<Integer> getDormIdList();

    /**
     * 根据日期查询电费信息
     *
     * @param start 开始
     * @param end   结束
     * @return {@link List}<{@link Electricity}>
     */
    public LineChartVo selectElectricityListByDate(String start, String end, Integer dormId);

    /**
     * 新增宿舍电费记录
     *
     * @param electricity 宿舍电费记录
     * @return 结果
     */
    public int insertElectricity(Electricity electricity);

    /**
     * 新增宿舍电费记录
     *
     * @param electricitys 宿舍电费记录
     * @return 结果
     */
    public int insertElectricitys(List<Electricity> electricitys);

    /**
     * 修改宿舍电费记录
     *
     * @param electricity 宿舍电费记录
     * @return 结果
     */
    public int updateElectricity(Electricity electricity);

    /**
     * 批量删除宿舍电费记录
     *
     * @param ids 需要删除的宿舍电费记录主键集合
     * @return 结果
     */
    public int deleteElectricityByIds(Long[] ids);

    /**
     * 删除宿舍电费记录信息
     *
     * @param dormId 宿舍电费记录主键
     * @return 结果
     */
    public int deleteElectricityByDormId(Long dormId);
}
