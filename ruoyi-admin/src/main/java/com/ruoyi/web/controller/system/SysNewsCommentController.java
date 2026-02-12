package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysNewsComment;
import com.ruoyi.system.service.ISysNewsCommentService;

/**
 * 新闻评论Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/news/comment")
public class SysNewsCommentController extends BaseController {
    @Autowired
    private ISysNewsCommentService sysNewsCommentService;

    /**
     * 新增新闻评论
     */
    @PostMapping("/add")
    public AjaxResult addComment(@RequestBody SysNewsComment comment) {
        // 填充创建者（若依框架获取当前登录用户）
        comment.setCreateBy(getUsername());
        return toAjax(sysNewsCommentService.insertSysNewsComment(comment));
    }

    /**
     * 根据新闻ID查询评论列表
     */
    @GetMapping("/list")
    public AjaxResult getCommentList(@RequestParam("newsId") Long newsId) {
        List<SysNewsComment> list = sysNewsCommentService.selectSysNewsCommentByNewsId(newsId);
        return AjaxResult.success(list);
    }

    /**
     * 评论点赞
     */
    @PostMapping("/like")
    public AjaxResult likeComment(@RequestParam("commentId") Long commentId) {
        return toAjax(sysNewsCommentService.likeSysNewsComment(commentId));
    }
}