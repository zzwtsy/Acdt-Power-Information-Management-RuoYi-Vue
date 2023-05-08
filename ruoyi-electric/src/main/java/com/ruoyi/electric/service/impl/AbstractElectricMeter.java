package com.ruoyi.electric.service.impl;

import com.ruoyi.electric.MyHeader;
import com.ruoyi.electric.api.Api;
import com.ruoyi.electric.domain.ElectricityBean;
import com.ruoyi.electric.enums.ElectricMeterType;
import com.ruoyi.electric.service.IElectricMeterService;
import com.ruoyi.electric.service.ParseService;
import com.ruoyi.electric.tools.Const;
import okhttp3.FormBody;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

abstract class AbstractElectricMeter implements IElectricMeterService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final Api api;

    AbstractElectricMeter() {
        this.api = new Api();
    }

    /**
     * 绑定空调电费类型
     *
     * @param dormId 宿舍号
     * @return boolean
     */
    public boolean bindingAirElectricMeterType(@NotNull String dormId) {
        return bindElectricMeterType(ElectricMeterType.AIR, dormId);
    }

    /**
     * 绑定照明电费类型
     *
     * @param dormId 宿舍号
     * @return boolean
     */
    public boolean bindingLightingElectricMeterType(@NotNull String dormId) {
        return bindElectricMeterType(ElectricMeterType.LIGHTING, dormId);
    }

    /**
     * 获取用电数据
     *
     * @param page 分页 id
     * @return {@link List}<{@link ElectricityBean}>
     */
    protected List<ElectricityBean> getElectricData(@NotNull Integer page) {

        String dormElectricityData = null;
        try {
            dormElectricityData = api.getDormElectricityData(page, Const.COOKIE);
        } catch (IOException e) {
            log.error("获取电费信息发生错误：", e);
            throw new RuntimeException(e);
        }

        // 每请求 5 次暂停 200 毫秒
        if (page % 4 == 0) {
            try {
                // 请求过快会停止响应
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                log.error("请求用电数据发生错误：", e);
                throw new IllegalStateException("请求用电数据发生错误");
            }
        }

        if (dormElectricityData == null || dormElectricityData.isEmpty()) {
            return new ArrayList<>();
        }

        return new ParseService().electricityUsage(dormElectricityData);
    }

    /**
     * 绑定电表类型
     *
     * @param type   类型
     * @param dormId 宿舍号
     * @return boolean
     */
    private boolean bindElectricMeterType(ElectricMeterType type, @NotNull String dormId) {
        //dormId: 07408
        String dormitoryBuildingNo;
        if (dormId.startsWith("0")) {
            dormitoryBuildingNo = dormId.substring(1, 2);
        } else {
            dormitoryBuildingNo = dormId.substring(0, 2);
        }
        // 408
        final String dormitoryNo = dormId.substring(2);
        // 4
        final String dormitoryFloors = dormId.substring(2, 3);
        // 7号楼照明/4层/7408
        final String queryParam = dormitoryBuildingNo + "号楼"
                + type.getTypeName() + "/"
                + dormitoryFloors + "层/"
                + dormitoryBuildingNo + dormitoryNo;

        String roomdm;
        if (type == ElectricMeterType.AIR) {
            roomdm = "190" + dormitoryNo;
        } else {
            roomdm = "080" + dormitoryNo;
        }

        Headers header = MyHeader.header(Const.COOKIE);
        FormBody body = new FormBody.Builder()
                .add("openid", Const.wechatOpenid)
                .add("roomdm", roomdm)
                .add("room", queryParam)
                .add("mode", "u")
                .build();
        try {
            return api.bindingElectricMeterType(header, body, queryParam);
        } catch (IOException e) {
            log.error("绑定" + type.getTypeName() + "电费类型失败：", e);
            return false;
        }
    }
}

