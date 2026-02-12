package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysNewsComment;
import org.apache.ibatis.annotations.Param;

/**
 * 新闻评论Mapper接口
 *
 * @author ruoyi
 */
public interface SysNewsCommentMapper {
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
    public List<SysNewsComment> selectSysNewsCommentByNewsId(@Param("newsId") Long newsId);

    /**
     * 修改评论点赞数
     *
     * @param commentId 评论ID
     * @param likeCount 点赞数
     * @return 结果
     */
    public int updateSysNewsCommentLikeCount(@Param("commentId") Long commentId, @Param("likeCount") Integer likeCount);

    /**
     * 修改评论状态（禁用/启用）
     *
     * @param commentId 评论ID
     * @param status 状态
     * @return 结果
     */
    public int updateSysNewsCommentStatus(@Param("commentId") Long commentId, @Param("status") String status);

    /**
     * 通过评论ID查询单条评论
     * @param commentId 评论ID
     * @return 评论对象
     */
    public SysNewsComment selectSysNewsCommentByCommentId(Long commentId);
}