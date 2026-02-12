package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import java.util.Date;

/**
 * 新闻评论实体类
 *
 * @author ruoyi
 */
@Data
@TableName("sys_news_comment")
public class SysNewsComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    @TableId(type = IdType.AUTO)
    private Long commentId;

    /** 关联新闻ID */
    private Long newsId;

    /** 评论用户ID */
    private Long userId;

    /** 评论用户名 */
    private String userName;

    /** 评论内容 */
    private String content;

    /** 父评论ID */
    private Long parentId;

    /** 点赞数 */
    private Integer likeCount;

    /** 状态（0正常 1禁用） */
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}