package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.domain.SysNewsAttachment;
import com.ruoyi.system.mapper.SysNewsAttachmentMapper;
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

    @Autowired
    private SysNewsAttachmentMapper sysNewsAttachmentMapper;


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
    public int insertSysNews(SysNews sysNews) {
        // 1. 先插入新闻主表，获取自增的 newsId
        int rows = sysNewsMapper.insertSysNews(sysNews);
        Long newsId = sysNews.getNewsId();

        // 2. 如果前端传了 attachmentList，批量插入附件表
        List<SysNewsAttachment> attachmentList = sysNews.getAttachmentList();
        if (StringUtils.isNotEmpty(attachmentList)) {
            for (SysNewsAttachment attachment : attachmentList) {
                // 绑定新闻ID
                attachment.setNewsId(newsId);
                // 调用附件Mapper插入（你之前已经写好的insertSysNewsAttachment）
                sysNewsAttachmentMapper.insertSysNewsAttachment(attachment);
            }
        }
        return rows;
    }

    @Override
    public int updateSysNews(SysNews sysNews) {
        Long newsId = sysNews.getNewsId();

        // 1. 先更新新闻主表
        int rows = sysNewsMapper.updateSysNews(sysNews);

        // 2. 先删除该新闻下的所有旧附件（可选，根据业务需要）
        sysNewsAttachmentMapper.deleteSysNewsAttachmentByNewsId(newsId);

        // 3. 如果前端传了新的 attachmentList，重新插入
        List<SysNewsAttachment> attachmentList = sysNews.getAttachmentList();
        if (StringUtils.isNotEmpty(attachmentList)) {
            for (SysNewsAttachment attachment : attachmentList) {
                attachment.setNewsId(newsId);
                sysNewsAttachmentMapper.insertSysNewsAttachment(attachment);
            }
        }
        return rows;
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