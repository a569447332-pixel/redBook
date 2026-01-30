package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 新闻对象 sys_news
 *
 * @author ruoyi
 * @date 2026-01-30
 */
@Data
public class SysNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻ID */
    private Long newsId;

    /** 新闻标题 */
    @Excel(name = "新闻标题")
    private String title;

    /** 所属分类ID */
    private Long categoryId;

    /** 分类名称（关联查询用） */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 新闻内容 */
    private String content;

    /** 类型（1图文 2视频） */
    @Excel(name = "类型", readConverterExp = "1=图文,2=视频")
    private String type;

    /** 封面图片 */
    private String cover;

    /** 视频地址 */
    private String videoUrl;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long viewCount;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
}