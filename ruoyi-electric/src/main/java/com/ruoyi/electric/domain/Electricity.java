package com.ruoyi.electric.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宿舍电费记录对象 electricity
 * 
 * @author ruoyi
 * @date 2023-05-04
 */
public class Electricity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 宿舍号 */
    @Excel(name = "宿舍号")
    private Integer dormitoryId;

    /** 照明电费用量 */
    @Excel(name = "照明电费用量")
    private Double lightingElectricityUsage;

    /** 空调电费用量 */
    @Excel(name = "空调电费用量")
    private Double airElectricityUsage;

    /** 用电日期 */
    @Excel(name = "用电日期")
    private String date;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setDormitoryId(Integer dormitoryId)
    {
        this.dormitoryId = dormitoryId;
    }

    public Integer getDormitoryId()
    {
        return dormitoryId;
    }
    public void setLightingElectricityUsage(Double lightingElectricityUsage)
    {
        this.lightingElectricityUsage = lightingElectricityUsage;
    }

    public Double getLightingElectricityUsage()
    {
        return lightingElectricityUsage;
    }
    public void setAirElectricityUsage(Double airElectricityUsage)
    {
        this.airElectricityUsage = airElectricityUsage;
    }

    public Double getAirElectricityUsage()
    {
        return airElectricityUsage;
    }
    public void setDate(String date) 
    {
        this.date = date;
    }

    public String getDate() 
    {
        return date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dormitoryId", getDormitoryId())
            .append("lightingElectricityUsage", getLightingElectricityUsage())
            .append("airElectricityUsage", getAirElectricityUsage())
            .append("date", getDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
