package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysNewsComment;

/**
 * 新闻评论Service接口
 *
 * @author ruoyi
 */
public interface ISysNewsCommentService {
    /**
     * 新增新闻评论
     *
     * @param sysNewsComment 新闻评论对象
     * @return 结果
     */
    public int insertSysNewsComment(SysNewsComment sysNewsComment);

    /**
     * 根据新闻ID查询评论列表
     *
     * @param newsId 新闻ID
     * @return 评论列表
     */
    public List<SysNewsComment> selectSysNewsCommentByNewsId(Long newsId);

    /**
     * 点赞评论
     *
     * @param commentId 评论ID
     * @return 结果
     */
    public int likeSysNewsComment(Long commentId);
}