package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysNewsCommentMapper;
import com.ruoyi.system.domain.SysNewsComment;
import com.ruoyi.system.service.ISysNewsCommentService;

/**
 * 新闻评论Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysNewsCommentServiceImpl implements ISysNewsCommentService {
    @Autowired
    private SysNewsCommentMapper sysNewsCommentMapper;

    @Override
    public int insertSysNewsComment(SysNewsComment sysNewsComment) {
        // 默认状态为正常
        if (sysNewsComment.getStatus() == null) {
            sysNewsComment.setStatus("0");
        }
        // 默认点赞数为0
        if (sysNewsComment.getLikeCount() == null) {
            sysNewsComment.setLikeCount(0);
        }
        return sysNewsCommentMapper.insertSysNewsComment(sysNewsComment);
    }

    @Override
    public List<SysNewsComment> selectSysNewsCommentByNewsId(Long newsId) {
        return sysNewsCommentMapper.selectSysNewsCommentByNewsId(newsId);
    }

    @Override
    public int likeSysNewsComment(Long commentId) {
        // 查询当前点赞数，+1后更新
        SysNewsComment comment = sysNewsCommentMapper.selectSysNewsCommentByCommentId(commentId);
        if (comment == null) {
            return 0;
        }
        int newLikeCount = comment.getLikeCount() + 1;
        return sysNewsCommentMapper.updateSysNewsCommentLikeCount(commentId, newLikeCount);
    }
}