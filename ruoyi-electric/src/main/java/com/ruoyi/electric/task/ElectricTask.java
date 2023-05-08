package com.ruoyi.electric.task;

import com.ruoyi.electric.api.Api;
import com.ruoyi.electric.domain.Electricity;
import com.ruoyi.electric.domain.ElectricityBean;
import com.ruoyi.electric.domain.ElectricityConfig;
import com.ruoyi.electric.enums.ElectricMeterType;
import com.ruoyi.electric.service.IElectricMeterService;
import com.ruoyi.electric.service.IElectricityConfigService;
import com.ruoyi.electric.service.IElectricityService;
import com.ruoyi.electric.tools.Const;
import com.ruoyi.electric.tools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("electricTask")
@SuppressWarnings("unused")
public class ElectricTask {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final IElectricityService electricityService;
    private final IElectricMeterService electricMeterService;
    private final IElectricityConfigService electricityConfigService;
    private String[] dormIds;

    @Autowired
    public ElectricTask(IElectricityConfigService electricityConfigService, IElectricityService electricityService, IElectricMeterService electricMeterService) {
        this.electricityConfigService = electricityConfigService;
        this.electricityService = electricityService;
        this.electricMeterService = electricMeterService;

    }

    public Boolean init() {
        ElectricityConfig electricityConfig = electricityConfigService.selectElectricityConfig();
        Const.wechatOpenid = electricityConfig.getWechatOpenid();
        Const.COOKIE = electricityConfig.getCookie();

        dormIds = Tools.stringToArray(electricityConfig.getDormIds());

        if (Tools.isCookieExpired()) {
            String cookie = new Api().getCookie();
            electricityConfig.setCookie(cookie);
            Const.COOKIE = cookie;
            if (cookie == null || Tools.isCookieExpired()) {
                log.error("没有 cookie，定时任务中止执行");
                return false;
            }
            electricityConfigService.updateElectricityConfig(electricityConfig);
        }
        return true;
    }

    /**
     * 全量更新
     */
    public void fullUpdate() {
        if (!init()) {
            return;
        }
        if (dormIds == null || dormIds.length == 0) {
            log.warn("没有宿舍门牌号，定时任务中止执行");
            return;
        }

        for (String dormId : dormIds) {

            List<Electricity> listAll = new ArrayList<>();
            Map<String, Map<ElectricMeterType, Double>> map = new HashMap<>();

            List<ElectricityBean> airElectricDataAll = electricMeterService.getAirElectricDataAll(dormId);
            List<ElectricityBean> lightingElectricDataAll = electricMeterService.getLightingElectricDataAll(dormId);

            airElectricDataAll.forEach(e -> {
                Map<ElectricMeterType, Double> airMap = new HashMap<>();
                airMap.put(ElectricMeterType.AIR, e.getElectricityUsage());
                map.put(e.getDate(), airMap);
            });
            lightingElectricDataAll.forEach(e -> map.get(e.getDate()).put(ElectricMeterType.LIGHTING, e.getElectricityUsage()));

            map.forEach((k, v) -> {
                Electricity electricity = new Electricity();
                electricity.setDate(k);
                electricity.setDormitoryId(Integer.valueOf(dormId));
                electricity.setAirElectricityUsage(v.get(ElectricMeterType.AIR));
                electricity.setLightingElectricityUsage(v.get(ElectricMeterType.LIGHTING));
                listAll.add(electricity);
            });

            electricityService.insertElectricitys(listAll);
        }

    }

    /**
     * 增量更新
     */
    public void incrementalUpdate() {
        if (!init()) {
            return;
        }
        if (dormIds == null || dormIds.length == 0) {
            log.warn("没有宿舍门牌号，定时任务中止执行");
            return;
        }

        List<Electricity> electricitys = new ArrayList<>();

        for (String dormId : dormIds) {

            ElectricityBean airElectricDataAll = electricMeterService.getAirElectricData(dormId);
            ElectricityBean lightingElectricDataAll = electricMeterService.getLightingElectricData(dormId);

            Electricity electricity = new Electricity();
            electricity.setDate(airElectricDataAll.getDate());
            electricity.setDormitoryId(Integer.valueOf(dormId));
            electricity.setAirElectricityUsage(airElectricDataAll.getElectricityUsage());
            electricity.setLightingElectricityUsage(lightingElectricDataAll.getElectricityUsage());

            electricitys.add(electricity);
        }

        electricityService.insertElectricitys(electricitys);
    }
}
