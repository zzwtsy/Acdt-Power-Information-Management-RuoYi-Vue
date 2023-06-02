package com.ruoyi.electric.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class LineChartVo {
    private List<String> date;
    private List<Double> lightingElectricityUsageData;
    private List<Double> airElectricityUsageData;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Double> getLightingElectricityUsageData() {
        return lightingElectricityUsageData;
    }

    public void setLightingElectricityUsageData(List<Double> lightingElectricityUsageData) {
        this.lightingElectricityUsageData = lightingElectricityUsageData;
    }

    public List<Double> getAirElectricityUsageData() {
        return airElectricityUsageData;
    }

    public void setAirElectricityUsageData(List<Double> airElectricityUsageData) {
        this.airElectricityUsageData = airElectricityUsageData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("date", getDate())
                .append("lightingElectricityUsageData", getLightingElectricityUsageData())
                .append("airElectricityUsageData", getAirElectricityUsageData())
                .toString();
    }
}
