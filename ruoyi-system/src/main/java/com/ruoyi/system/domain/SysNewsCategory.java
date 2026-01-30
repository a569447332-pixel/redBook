package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 新闻分类对象 sys_news_category
 *
 * @author ruoyi
 * @date 2026-01-30
 */
@Data
public class SysNewsCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 父分类ID */
    private Long parentId;

    /** 分类层级 */
    private Integer level;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 子分类列表 */
    private List<SysNewsCategory> children = new ArrayList<>();
}