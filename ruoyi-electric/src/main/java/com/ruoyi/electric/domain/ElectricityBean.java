package com.ruoyi.electric.domain;

public class ElectricityBean {
    private String date;
    private Integer dormitoryId;
    private Double electricityUsage;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Double getElectricityUsage() {
        return electricityUsage;
    }

    public void setElectricityUsage(Double electricityUsage) {
        this.electricityUsage = electricityUsage;
    }
}
