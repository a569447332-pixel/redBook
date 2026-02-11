package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.mapper.SysNewsMapper;
import com.ruoyi.system.service.ISysNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻Service业务层处理
 *
 * @author ruoyi
 * @date 2026-01-30
 */
@Service
public class SysNewsServiceImpl implements ISysNewsService
{
    @Autowired
    private SysNewsMapper sysNewsMapper;


    /**
     * 查询新闻列表
     */

    @Override
    public List<SysNews> selectNewsByCategoryIds(SysNews sysNews) {
        return sysNewsMapper.selectNewsByCategoryIds(sysNews);
    }

    /**
     * 查询新闻列表
     */
    @Override
    public List<SysNews> selectSysNewsList(SysNews sysNews)
    {
        return sysNewsMapper.selectSysNewsList(sysNews);
    }

    /**
     * 通过新闻ID查询新闻
     */
    @Override
    public SysNews selectSysNewsById(Long newsId)
    {
        return sysNewsMapper.selectSysNewsById(newsId);
    }

    /**
     * 新增新闻
     */
    @Override
    public int insertSysNews(SysNews sysNews)
    {
        return sysNewsMapper.insertSysNews(sysNews);
    }

    /**
     * 修改新闻
     */
    @Override
    public int updateSysNews(SysNews sysNews)
    {
        return sysNewsMapper.updateSysNews(sysNews);
    }


    /**
     * 删除新闻信息
     */
    @Override
    public int deleteSysNewsById(Long newsId)
    {
        return sysNewsMapper.deleteSysNewsById(newsId);
    }
}