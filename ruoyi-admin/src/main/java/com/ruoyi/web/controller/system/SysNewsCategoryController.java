package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysNewsCategory;
import com.ruoyi.system.service.ISysNewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 新闻分类Controller
 *
 * @author ruoyi
 * @date 2026-01-30
 */
@RestController
@RequestMapping("/system/news/category")
public class SysNewsCategoryController extends BaseController
{
    @Autowired
    private ISysNewsCategoryService sysNewsCategoryService;

    /**
     * 查询新闻分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:news:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNewsCategory sysNewsCategory)
    {
        startPage();
        List<SysNewsCategory> list = sysNewsCategoryService.selectSysNewsCategoryList(sysNewsCategory);
        return getDataTable(list);
    }

    /**
     * 查询新闻分类树形结构
     */
    @PreAuthorize("@ss.hasPermi('system:news:category:query')")
    @GetMapping("/tree")
    public AjaxResult tree(SysNewsCategory sysNewsCategory)
    {
        List<SysNewsCategory> list = sysNewsCategoryService.selectSysNewsCategoryTree(sysNewsCategory);
        return AjaxResult.success(list);
    }

    /**
     * 获取新闻分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:news:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable Long categoryId)
    {
        return AjaxResult.success(sysNewsCategoryService.selectSysNewsCategoryById(categoryId));
    }

    /**
     * 新增新闻分类
     */
    @PreAuthorize("@ss.hasPermi('system:news:category:add')")
    @Log(title = "新闻分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysNewsCategory sysNewsCategory)
    {
        return toAjax(sysNewsCategoryService.insertSysNewsCategory(sysNewsCategory));
    }

    /**
     * 修改新闻分类
     */
    @PreAuthorize("@ss.hasPermi('system:news:category:edit')")
    @Log(title = "新闻分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysNewsCategory sysNewsCategory)
    {
        return toAjax(sysNewsCategoryService.updateSysNewsCategory(sysNewsCategory));
    }

    /**
     * 删除新闻分类
     */
    @PreAuthorize("@ss.hasPermi('system:news:category:remove')")
    @Log(title = "新闻分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(sysNewsCategoryService.deleteSysNewsCategoryById(categoryIds[0]));
    }
}