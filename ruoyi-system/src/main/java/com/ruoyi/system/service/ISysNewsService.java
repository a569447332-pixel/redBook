package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysNews;
import java.util.List;

/**
 * 新闻Service接口
 *
 * @author ruoyi
 * @date 2026-01-30
 */
public interface ISysNewsService
{
    /**
     * 查询新闻列表
     *
     * @param sysNews 新闻
     * @return 新闻集合
     */
    public List<SysNews> selectSysNewsList(SysNews sysNews);

    /**
     * 通过新闻ID查询新闻
     *
     * @param newsId 新闻ID
     * @return 新闻信息
     */
    public SysNews selectSysNewsById(Long newsId);

    /**
     * 新增新闻
     *
     * @param sysNews 新闻
     * @return 结果
     */
    public int insertSysNews(SysNews sysNews);

    /**
     * 修改新闻
     *
     * @param sysNews 新闻
     * @return 结果
     */
    public int updateSysNews(SysNews sysNews);

    /**
     * 删除新闻信息
     *
     * @param newsId 新闻ID
     * @return 结果
     */
    public int deleteSysNewsById(Long newsId);
}