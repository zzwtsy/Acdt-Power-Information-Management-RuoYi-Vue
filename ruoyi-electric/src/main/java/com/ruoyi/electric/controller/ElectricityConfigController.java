package com.ruoyi.electric.controller;

import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.electric.domain.ElectricityConfig;
import com.ruoyi.electric.service.IElectricityConfigService;

import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍电费信息的相关配置Controller
 *
 * @author ruoyi
 * @date 2023-05-08
 */
@RestController
@RequestMapping("/electric/ElectricConfig")
public class ElectricityConfigController extends BaseController {
    @Autowired
    private IElectricityConfigService electricityConfigService;


    /**
     * 获取宿舍电费信息的相关配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('electric:ElectricConfig:query')")
    @GetMapping(value = "/info")
    public TableDataInfo getInfo() {
        startPage();
        ElectricityConfig electricityConfig = electricityConfigService.selectElectricityConfig();
        List<ElectricityConfig> list = new ArrayList<>();
        list.add(electricityConfig);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('electric:ElectricConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getData() {
        return AjaxResult.success(electricityConfigService.selectElectricityConfig());
    }

    /**
     * 新增宿舍电费信息的相关配置
     */
    @PreAuthorize("@ss.hasPermi('electric:ElectricConfig:add')")
    @Log(title = "宿舍电费信息的相关配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ElectricityConfig electricityConfig) {
        return toAjax(electricityConfigService.insertElectricityConfig(electricityConfig));
    }

    /**
     * 修改宿舍电费信息的相关配置
     */
    @PreAuthorize("@ss.hasPermi('electric:ElectricConfig:edit')")
    @Log(title = "宿舍电费信息的相关配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ElectricityConfig electricityConfig) {
        return toAjax(electricityConfigService.updateElectricityConfig(electricityConfig));
    }

    /**
     * 删除宿舍电费信息的相关配置
     */
    @PreAuthorize("@ss.hasPermi('electric:ElectricConfig:remove')")
    @Log(title = "宿舍电费信息的相关配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(electricityConfigService.deleteElectricityConfigByCookies(ids));
    }
}
