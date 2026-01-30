package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysNewsCategory;
import com.ruoyi.system.mapper.SysNewsCategoryMapper;
import com.ruoyi.system.service.ISysNewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 新闻分类Service业务层处理
 *
 * @author ruoyi
 * @date 2026-01-30
 */
@Service
public class SysNewsCategoryServiceImpl implements ISysNewsCategoryService
{
    @Autowired
    private SysNewsCategoryMapper sysNewsCategoryMapper;

    /**
     * 查询新闻分类列表
     */
    @Override
    public List<SysNewsCategory> selectSysNewsCategoryList(SysNewsCategory sysNewsCategory)
    {
        return sysNewsCategoryMapper.selectSysNewsCategoryList(sysNewsCategory);
    }

    /**
     * 查询新闻分类树形结构
     */
    @Override
    public List<SysNewsCategory> selectSysNewsCategoryTree(SysNewsCategory sysNewsCategory)
    {
        List<SysNewsCategory> list = sysNewsCategoryMapper.selectSysNewsCategoryTree(sysNewsCategory);
        return buildCategoryTree(list);
    }

    /**
     * 构建新闻分类树形结构
     */
    private List<SysNewsCategory> buildCategoryTree(List<SysNewsCategory> list)
    {
        return list.stream()
                .filter(category -> category.getParentId() == 0)
                .peek(root -> root.setChildren(getChildren(root, list)))
                .collect(Collectors.toList());
    }

    /**
     * 获取子分类列表
     */
    private List<SysNewsCategory> getChildren(SysNewsCategory parent, List<SysNewsCategory> list)
    {
        return list.stream()
                .filter(category -> StringUtils.equals(category.getParentId().toString(), parent.getCategoryId().toString()))
                .peek(child -> child.setChildren(getChildren(child, list)))
                .collect(Collectors.toList());
    }

    /**
     * 通过分类ID查询新闻分类
     */
    @Override
    public SysNewsCategory selectSysNewsCategoryById(Long categoryId)
    {
        return sysNewsCategoryMapper.selectSysNewsCategoryById(categoryId);
    }

    /**
     * 新增新闻分类
     */
    @Override
    public int insertSysNewsCategory(SysNewsCategory sysNewsCategory)
    {
        SysNewsCategory parent = sysNewsCategoryMapper.selectSysNewsCategoryById(sysNewsCategory.getParentId());
        if (parent != null)
        {
            // 子分类层级 = 父分类层级 + 1
            sysNewsCategory.setLevel(parent.getLevel() + 1);
        }
        else
        {
            // 顶级分类层级为1
            sysNewsCategory.setLevel(1);
        }
        return sysNewsCategoryMapper.insertSysNewsCategory(sysNewsCategory);
    }

    /**
     * 修改新闻分类
     */
    @Override
    public int updateSysNewsCategory(SysNewsCategory sysNewsCategory)
    {
        return sysNewsCategoryMapper.updateSysNewsCategory(sysNewsCategory);
    }

    /**
     * 删除新闻分类信息
     */
    @Override
    public int deleteSysNewsCategoryById(Long categoryId)
    {
        return sysNewsCategoryMapper.deleteSysNewsCategoryById(categoryId);
    }
}