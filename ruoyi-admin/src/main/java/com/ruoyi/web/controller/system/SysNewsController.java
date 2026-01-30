package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.service.ISysNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 新闻Controller
 *
 * @author ruoyi
 * @date 2026-01-30
 */
@RestController
@RequestMapping("/system/news")
public class SysNewsController extends BaseController
{
    @Autowired
    private ISysNewsService sysNewsService;

    /**
     * 查询新闻列表
     */
    @PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNews sysNews)
    {
        startPage();
        List<SysNews> list = sysNewsService.selectSysNewsList(sysNews);
        return getDataTable(list);
    }

    /**
     * 获取新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = "/{newsId}")
    public AjaxResult getInfo(@PathVariable Long newsId)
    {
        return AjaxResult.success(sysNewsService.selectSysNewsById(newsId));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysNews sysNews)
    {
        return toAjax(sysNewsService.insertSysNews(sysNews));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysNews sysNews)
    {
        return toAjax(sysNewsService.updateSysNews(sysNews));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable Long[] newsIds)
    {
        return toAjax(sysNewsService.deleteSysNewsById(newsIds[0]));
    }
}