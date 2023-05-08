package com.ruoyi.electric.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.electric.domain.ElectricityBean;
import com.ruoyi.electric.domain.ElectricityJson;
import com.ruoyi.electric.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParseService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public List<ElectricityBean> electricityUsage(String content) {
        List<ElectricityBean> list = new ArrayList<>();
        String jsonContent = content
                .replace("[{", "{")
                .replace("]}", "}")
                .replace("result", "\"result\"")
                .replace("currentPage", "\"currentPage\"");

        ElectricityJson electricityData;
        try {
            electricityData = JsonUtils.stringToObject(jsonContent, ElectricityJson.class);
        } catch (JsonProcessingException e) {
            log.error("解析电费信息失败：", e);
            throw new RuntimeException(e);
        }

        if (electricityData == null) return list;

        electricityData.getResult().forEach(e -> {
            ElectricityBean electricityBean = new ElectricityBean();
            electricityBean.setDate(e.get(0).split(" ")[0]);
            electricityBean.setElectricityUsage(Double.valueOf(e.get(1)));
            electricityBean.setDormitoryId(Integer.valueOf(e.get(2)));
            list.add(electricityBean);
        });
        return list;
    }
}
