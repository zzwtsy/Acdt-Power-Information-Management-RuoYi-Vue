package com.ruoyi.electric.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ElectricityJson {
    @JsonProperty("result")
    private List<List<String>> result;
    @JsonProperty("currentPage")
    private Integer currentPage;

    public List<List<String>> getResult() {
        return result;
    }

    public void setResult(List<List<String>> result) {
        this.result = result;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
