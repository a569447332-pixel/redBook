package com.ruoyi.web.controller.system;

import java.util.List;
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
import com.ruoyi.system.domain.SysAdvertisement;
import com.ruoyi.system.service.ISysAdvertisementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 广告Controller
 *
 * @author ruoyi
 * @date 2026-02-11
 */
@RestController
@RequestMapping("/system/advertisement")
public class SysAdvertisementController extends BaseController
{
    @Autowired
    private ISysAdvertisementService sysAdvertisementService;

    /**
     * 查询广告列表
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAdvertisement sysAdvertisement)
    {
        startPage();
        List<SysAdvertisement> list = sysAdvertisementService.selectSysAdvertisementList(sysAdvertisement);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:export')")
    @Log(title = "广告", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysAdvertisement sysAdvertisement)
    {
        List<SysAdvertisement> list = sysAdvertisementService.selectSysAdvertisementList(sysAdvertisement);
        ExcelUtil<SysAdvertisement> util = new ExcelUtil<SysAdvertisement>(SysAdvertisement.class);
        return util.exportExcel(list, "广告数据");
    }

    /**
     * 获取广告详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:query')")
    @GetMapping(value = "/{adId}")
    public AjaxResult getInfo(@PathVariable("adId") Long adId)
    {
        return success(sysAdvertisementService.selectSysAdvertisementByAdId(adId));
    }

    /**
     * 新增广告
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:add')")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAdvertisement sysAdvertisement)
    {
        return toAjax(sysAdvertisementService.insertSysAdvertisement(sysAdvertisement));
    }

    /**
     * 修改广告
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:edit')")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAdvertisement sysAdvertisement)
    {
        System.out.println("接收到的广告数据: " + sysAdvertisement);
        return toAjax(sysAdvertisementService.updateSysAdvertisement(sysAdvertisement));
    }

    /**
     * 删除广告
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:remove')")
    @Log(title = "广告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{adIds}")
    public AjaxResult remove(@PathVariable Long[] adIds)
    {
        return toAjax(sysAdvertisementService.deleteSysAdvertisementByAdIds(adIds));
    }
}