package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysNewsCategory;
import java.util.List;

/**
 * 新闻分类Mapper接口
 *
 * @author ruoyi
 * @date 2026-01-30
 */
public interface SysNewsCategoryMapper
{
    /**
     * 查询新闻分类列表
     *
     * @param sysNewsCategory 新闻分类
     * @return 新闻分类集合
     */
    public List<SysNewsCategory> selectSysNewsCategoryList(SysNewsCategory sysNewsCategory);

    /**
     * 查询新闻分类树形结构
     *
     * @param sysNewsCategory 新闻分类
     * @return 新闻分类树形集合
     */
    public List<SysNewsCategory> selectSysNewsCategoryTree(SysNewsCategory sysNewsCategory);

    /**
     * 通过分类ID查询新闻分类
     *
     * @param categoryId 分类ID
     * @return 新闻分类信息
     */
    public SysNewsCategory selectSysNewsCategoryById(Long categoryId);

    /**
     * 新增新闻分类
     *
     * @param sysNewsCategory 新闻分类
     * @return 结果
     */
    public int insertSysNewsCategory(SysNewsCategory sysNewsCategory);

    /**
     * 修改新闻分类
     *
     * @param sysNewsCategory 新闻分类
     * @return 结果
     */
    public int updateSysNewsCategory(SysNewsCategory sysNewsCategory);

    /**
     * 删除新闻分类
     *
     * @param categoryId 分类ID
     * @return 结果
     */
    public int deleteSysNewsCategoryById(Long categoryId);
}