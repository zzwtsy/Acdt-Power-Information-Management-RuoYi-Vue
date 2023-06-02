package com.ruoyi.electric.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.electric.domain.vo.LineChartVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.electric.domain.Electricity;
import com.ruoyi.electric.service.IElectricityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宿舍电费记录Controller
 *
 * @author ruoyi
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/api/electricity")
public class ElectricityController extends BaseController {
    @Autowired
    private IElectricityService electricityService;

    /**
     * 查询宿舍电费记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:electricity:list')")
    @GetMapping("/list")
    public TableDataInfo list(Electricity electricity) {
        startPage();
        List<Electricity> list = electricityService.selectElectricityList(electricity);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:electricity:queryByDate')")
    @GetMapping("/queryByDate")
    public AjaxResult getElectricityListByDate(
            @RequestParam("start") String start,
            @RequestParam("end") String end,
            @RequestParam("dormId") Integer dormId
    ) {
        LineChartVo lineChartVo = electricityService.selectElectricityListByDate(start, end, dormId);

        if (lineChartVo == null) {
            return error("暂无数据");
        }
        return success(lineChartVo);
    }

    @PreAuthorize("@ss.hasPermi('system:electricity:queryDormIds')")
    @GetMapping("/queryDormIds")
    public List<Integer> getDormIdList() {
        return electricityService.getDormIdList();
    }

    /**
     * 导出宿舍电费记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:electricity:export')")
    @Log(title = "宿舍电费记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Electricity electricity) {
        List<Electricity> list = electricityService.selectElectricityList(electricity);
        ExcelUtil<Electricity> util = new ExcelUtil<Electricity>(Electricity.class);
        util.exportExcel(response, list, "宿舍电费记录数据");
    }

    /**
     * 获取宿舍电费记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:electricity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        Electricity electricity = electricityService.selectElectricityById(id);
        return success(electricity);
    }

    /**
     * 新增宿舍电费记录
     */
    @PreAuthorize("@ss.hasPermi('system:electricity:add')")
    @Log(title = "宿舍电费记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Electricity electricity) {
        return toAjax(electricityService.insertElectricity(electricity));
    }

    /**
     * 修改宿舍电费记录
     */
    @PreAuthorize("@ss.hasPermi('system:electricity:edit')")
    @Log(title = "宿舍电费记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Electricity electricity) {
        return toAjax(electricityService.updateElectricity(electricity));
    }

    /**
     * 删除宿舍电费记录
     */
    @PreAuthorize("@ss.hasPermi('system:electricity:remove')")
    @Log(title = "宿舍电费记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(electricityService.deleteElectricityByIds(ids));
    }
}
