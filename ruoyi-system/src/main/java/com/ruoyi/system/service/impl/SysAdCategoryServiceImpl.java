// 实现类
package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysAdCategoryMapper;
import com.ruoyi.system.domain.SysAdCategory;
import com.ruoyi.system.service.ISysAdCategoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 广告分类Service业务层处理
 *
 * @author ruoyi
 * @date 2026-02-11
 */
@Service
public class SysAdCategoryServiceImpl implements ISysAdCategoryService
{
    @Autowired
    private SysAdCategoryMapper sysAdCategoryMapper;

    /**
     * 查询广告分类
     *
     * @param categoryId 广告分类主键
     * @return 广告分类
     */
    @Override
    public SysAdCategory selectSysAdCategoryByCategoryId(Long categoryId)
    {
        return sysAdCategoryMapper.selectSysAdCategoryByCategoryId(categoryId);
    }

    /**
     * 查询广告分类列表
     *
     * @param sysAdCategory 广告分类
     * @return 广告分类
     */
    @Override
    public List<SysAdCategory> selectSysAdCategoryList(SysAdCategory sysAdCategory)
    {
        return sysAdCategoryMapper.selectSysAdCategoryList(sysAdCategory);
    }

    /**
     * 新增广告分类
     *
     * @param sysAdCategory 广告分类
     * @return 结果
     */
    @Override
    public int insertSysAdCategory(SysAdCategory sysAdCategory)
    {
        return sysAdCategoryMapper.insertSysAdCategory(sysAdCategory);
    }

    /**
     * 修改广告分类
     *
     * @param sysAdCategory 广告分类
     * @return 结果
     */
    @Override
    public int updateSysAdCategory(SysAdCategory sysAdCategory)
    {
        return sysAdCategoryMapper.updateSysAdCategory(sysAdCategory);
    }

    /**
     * 批量删除广告分类
     *
     * @param categoryIds 需要删除的广告分类主键
     * @return 结果
     */
    @Override
    public int deleteSysAdCategoryByCategoryIds(Long[] categoryIds)
    {
        return sysAdCategoryMapper.deleteSysAdCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除广告分类信息
     *
     * @param categoryId 广告分类主键
     * @return 结果
     */
    @Override
    public int deleteSysAdCategoryByCategoryId(Long categoryId)
    {
        return sysAdCategoryMapper.deleteSysAdCategoryByCategoryId(categoryId);
    }

    @Override
    public List<SysAdCategory> selectSysAdCategoryAll() {
        return sysAdCategoryMapper.selectSysAdCategoryAll();
    }
}