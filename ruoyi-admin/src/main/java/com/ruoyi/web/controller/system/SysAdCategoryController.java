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
import com.ruoyi.system.domain.SysAdCategory;
import com.ruoyi.system.service.ISysAdCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 广告分类Controller
 *
 * @author ruoyi
 * @date 2026-02-11
 */
@RestController
@RequestMapping("/system/adCategory")
public class SysAdCategoryController extends BaseController
{
    @Autowired
    private ISysAdCategoryService sysAdCategoryService;

    /**
     * 查询广告分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:adCategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAdCategory sysAdCategory)
    {
        startPage();
        List<SysAdCategory> list = sysAdCategoryService.selectSysAdCategoryList(sysAdCategory);
        return getDataTable(list);
    }

    /**
     * 导出广告分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:adCategory:export')")
    @Log(title = "广告分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysAdCategory sysAdCategory)
    {
        List<SysAdCategory> list = sysAdCategoryService.selectSysAdCategoryList(sysAdCategory);
        ExcelUtil<SysAdCategory> util = new ExcelUtil<SysAdCategory>(SysAdCategory.class);
        return util.exportExcel(list, "广告分类数据");
    }

    /**
     * 获取广告分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adCategory:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(sysAdCategoryService.selectSysAdCategoryByCategoryId(categoryId));
    }

    /**
     * 新增广告分类
     */
    @PreAuthorize("@ss.hasPermi('system:adCategory:add')")
    @Log(title = "广告分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAdCategory sysAdCategory)
    {
        return toAjax(sysAdCategoryService.insertSysAdCategory(sysAdCategory));
    }

    /**
     * 修改广告分类
     */
    @PreAuthorize("@ss.hasPermi('system:adCategory:edit')")
    @Log(title = "广告分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAdCategory sysAdCategory)
    {
        return toAjax(sysAdCategoryService.updateSysAdCategory(sysAdCategory));
    }

    /**
     * 删除广告分类
     */
    @PreAuthorize("@ss.hasPermi('system:adCategory:remove')")
    @Log(title = "广告分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(sysAdCategoryService.deleteSysAdCategoryByCategoryIds(categoryIds));
    }

    /**
     * 获取所有正常状态的广告分类（用于下拉选择）
     */
    @GetMapping("/all")
    public AjaxResult getAllAdCategory() {
        List<SysAdCategory> list = sysAdCategoryService.selectSysAdCategoryAll();
        return success(list);
    }
}