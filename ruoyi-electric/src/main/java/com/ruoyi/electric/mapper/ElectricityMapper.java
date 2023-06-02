package com.ruoyi.electric.mapper;

import java.util.List;

import com.ruoyi.electric.domain.Electricity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 宿舍电费记录Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-04
 */
@Repository
public interface ElectricityMapper {
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

    public List<Integer> selectDormIds();

    /**
     * 选择电力通过宿舍id列表
     *
     * @param start  开始
     * @param end    结束
     * @param dormId 宿舍id
     * @return {@link List}<{@link Electricity}>
     */
    public List<Electricity> selectElectricityListByDate(
            @Param("start") String start,
            @Param("end") String end,
            @Param("dormId") Integer dormId
    );

    /**
     * 新增宿舍电费记录
     *
     * @param electricity 宿舍电费记录
     * @return 结果
     */
    public int insertElectricity(Electricity electricity);

    /**
     * 批量新增宿舍电费记录
     *
     * @param electricitys 宿舍电费记录
     * @return 结果
     */
    public int insertElectricitys(@Param("electricitys") List<Electricity> electricitys);

    /**
     * 修改宿舍电费记录
     *
     * @param electricity 宿舍电费记录
     * @return 结果
     */
    public int updateElectricity(Electricity electricity);

    /**
     * 删除宿舍电费记录
     *
     * @param dormId 宿舍电费记录主键
     * @return 结果
     */
    public int deleteElectricityByDormId(Long dormId);

    /**
     * 批量删除宿舍电费记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElectricityByIds(Long[] ids);
}
