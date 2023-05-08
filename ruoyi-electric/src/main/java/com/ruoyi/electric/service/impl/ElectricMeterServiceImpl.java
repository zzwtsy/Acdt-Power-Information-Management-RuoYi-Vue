package com.ruoyi.electric.service.impl;

import com.ruoyi.electric.domain.ElectricityBean;
import com.ruoyi.electric.service.IElectricityService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ElectricMeterServiceImpl extends AbstractElectricMeter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IElectricityService electricityService;

    public ElectricMeterServiceImpl() {
        super();
    }

    /**
     * 获取所有空调用电数据
     *
     * @return {@link List}<{@link ElectricityBean}>
     */
    public List<ElectricityBean> getAirElectricDataAll(@NotNull String dormId) {
        if (!bindingAirElectricMeterType(dormId)) {
            log.error("绑定空调电费类型失败");
            return new ArrayList<>();
        }

        List<ElectricityBean> listAll = new ArrayList<>();
        for (int i = 1; ; i++) {
            if (i % 5 == 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    log.error("休眠失败");
                    throw new RuntimeException(e);
                }
            }
            List<ElectricityBean> electricData = getElectricData(i);
            if (electricData.isEmpty()) break;
            listAll.addAll(electricData);
        }

        return listAll;
    }

    /**
     * 获取所有照明用电数据
     *
     * @return {@link List}<{@link ElectricityBean}>
     */
    public List<ElectricityBean> getLightingElectricDataAll(@NotNull String dormId) {
        if (!bindingLightingElectricMeterType(dormId)) {
            log.error("绑定空调电费类型失败");
            return new ArrayList<>();
        }

        List<ElectricityBean> listAll = new ArrayList<>();
        for (int i = 1; ; i++) {
            if (i % 5 == 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    log.error("休眠失败");
                    throw new RuntimeException(e);
                }
            }
            List<ElectricityBean> electricData = getElectricData(i);
            if (electricData.isEmpty()) break;
            listAll.addAll(electricData);
        }

        return listAll;
    }

    /**
     * 获取第一条空调用电数据
     *
     * @param dormId 宿舍号
     * @return {@link List}<{@link ElectricityBean}>
     */
    public ElectricityBean getAirElectricData(@NotNull String dormId) {
        if (!bindingAirElectricMeterType(dormId)) {
            return new ElectricityBean();
        }
        Optional<ElectricityBean> first = getElectricData(1).stream().findFirst();
        // 不存在返回空 ElectricityBean
        return first.orElseGet(ElectricityBean::new);
    }

    /**
     * 获取第一条照明用电数据
     *
     * @param dormId 宿舍号
     * @return {@link List}<{@link ElectricityBean}>
     */
    @Override
    public ElectricityBean getLightingElectricData(@NotNull String dormId) {
        if (!bindingLightingElectricMeterType(dormId)) {
            return new ElectricityBean();
        }
        Optional<ElectricityBean> first = super.getElectricData(1).stream().findFirst();
        // 不存在返回空 ElectricityBean
        return first.orElseGet(ElectricityBean::new);
    }
}
