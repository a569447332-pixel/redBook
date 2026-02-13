package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.domain.SysNewsAttachment;
import com.ruoyi.system.domain.SysNewsCategory;
import com.ruoyi.system.domain.SysNewsComment;
import com.ruoyi.system.service.ISysNewsCommentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ruoyi.system.service.ISysNewsCategoryService;
import com.ruoyi.system.service.ISysNewsService;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Arrays; // 需导入该类
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * 客户端新闻匿名接口（无需登录）
 * @author ruoyi
 */
@RestController
@RequestMapping("/app/news") // 客户端接口统一前缀 /app
public class AppNewsController extends BaseController{
    @Autowired
    private ISysNewsCategoryService sysNewsCategoryService;

    @Autowired
    private ISysNewsService sysNewsService;

    @Autowired
    private ISysNewsCommentService sysNewsCommentService;


    /**
     * 1. 查询新闻分类树形结构（客户端展示分类导航）
     */
    @GetMapping("/category/tree")
    public AjaxResult getCategoryTree() {
        SysNewsCategory query = new SysNewsCategory();
        query.setStatus("0"); // 只返回正常状态的分类
        List<SysNewsCategory> tree = sysNewsCategoryService.selectSysNewsCategoryTree(query);
        return AjaxResult.success(tree);
    }

    /**
     * 2. 按分类ID查询新闻列表（支持图文/视频筛选）
     */
    @GetMapping("/list")
    public TableDataInfo  list(SysNews sysNews)
    {

        startPage();
        List<SysNews> list = sysNewsService.selectNewsByCategoryIds(sysNews);
        return getDataTable(list);

    }

    /**
     * 3. 查询新闻详情（图文/视频）
     * @param newsId 新闻ID
     */
    @GetMapping("/detail/{newsId}")
    public AjaxResult getNewsDetail(@RequestBody Long newsId) {

        SysNews news = sysNewsService.selectSysNewsById(newsId);
        if (news == null || "1".equals(news.getStatus())) {
            return AjaxResult.error("新闻不存在或已下架");
        }
        // 可选：浏览量+1（简单实现，实际可加锁）
        news.setViewCount(news.getViewCount() + 1);
        sysNewsService.updateSysNews(news);

        // 打印要发送的响应参数
        System.out.println("返回的新闻数据：" + news);

        return AjaxResult.success(news);
    }

    @PostMapping("/comment")
    public AjaxResult addComment(@RequestBody SysNewsComment comment) {

        // 判断用户是否已登录
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            // 未登录，直接抛出异常或返回提示
            throw new RuntimeException("请登录后再访问发表评论！");
        }

        // 填充创建者（若依框架获取当前登录用户）
        comment.setCreateBy(getUsername());
        return toAjax(sysNewsCommentService.insertSysNewsComment(comment));
    }

}