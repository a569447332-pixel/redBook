package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysAdCategory;

/**
 * 广告分类Mapper接口
 *
 * @author ruoyi
 * @date 2026-02-11
 */
public interface SysAdCategoryMapper
{
    /**
     * 查询广告分类
     *
     * @param categoryId 广告分类主键
     * @return 广告分类
     */
    public SysAdCategory selectSysAdCategoryByCategoryId(Long categoryId);

    /**
     * 查询广告分类列表
     *
     * @param sysAdCategory 广告分类
     * @return 广告分类集合
     */
    public List<SysAdCategory> selectSysAdCategoryList(SysAdCategory sysAdCategory);

    /**
     * 新增广告分类
     *
     * @param sysAdCategory 广告分类
     * @return 结果
     */
    public int insertSysAdCategory(SysAdCategory sysAdCategory);

    /**
     * 修改广告分类
     *
     * @param sysAdCategory 广告分类
     * @return 结果
     */
    public int updateSysAdCategory(SysAdCategory sysAdCategory);

    /**
     * 删除广告分类
     *
     * @param categoryId 广告分类主键
     * @return 结果
     */
    public int deleteSysAdCategoryByCategoryId(Long categoryId);

    /**
     * 批量删除广告分类
     *
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAdCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 查询所有正常状态的广告分类（用于下拉选择）
     * @return 分类列表
     */
    public List<SysAdCategory> selectSysAdCategoryAll();
}