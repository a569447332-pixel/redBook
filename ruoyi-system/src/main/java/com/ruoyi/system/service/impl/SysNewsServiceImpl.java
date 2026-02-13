package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysNews;
import com.ruoyi.system.domain.SysNewsAttachment;
import com.ruoyi.system.mapper.SysNewsAttachmentMapper;
import com.ruoyi.system.mapper.SysNewsMapper;
import com.ruoyi.system.service.ISysNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


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
    public SysNews selectSysNewsById(Long newsId) {
        // 1. 查询新闻主表信息
        SysNews sysNews = sysNewsMapper.selectSysNewsById(newsId);
        if (sysNews == null) {
            return null;
        }

        // 2. 关键：查询该新闻下的所有附件列表
        List<SysNewsAttachment> attachmentList = sysNewsAttachmentMapper.selectSysNewsAttachmentByNewsId(newsId);

        // 3. 将附件列表设置到新闻对象中（供前端获取）
        sysNews.setAttachmentList(attachmentList);

        return sysNews;
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
                System.out.println("接受到附件数据：" + attachment);

                sysNewsAttachmentMapper.insertSysNewsAttachment(attachment);
            }
        }
        return rows;
    }

    @Override
    public int updateSysNews(SysNews sysNews) {

        // 1. 先更新新闻主表
        int rows = sysNewsMapper.updateSysNews(sysNews);

        Long newsId = sysNews.getNewsId();

        // 2. 处理附件：新增/更新（不再删除所有旧附件）
        List<SysNewsAttachment> attachmentList = sysNews.getAttachmentList();
        // 存储前端传递的有效附件ID（用于后续删除冗余）
        List<Long> frontAttachmentIds = new ArrayList<>();
        if (StringUtils.isNotEmpty(attachmentList)) {
            for (SysNewsAttachment attachment : attachmentList) {
                attachment.setNewsId(newsId);
                // 设置更新人（若依框架获取当前登录用户）
                attachment.setUpdateBy(SecurityUtils.getUsername());
                attachment.setUpdateTime(new Date());

                if (attachment.getAttachmentId() == null || attachment.getAttachmentId() == 0) {
                    // 附件ID为0 → 新增
                    sysNewsAttachmentMapper.insertSysNewsAttachment(attachment);
                    System.out.println("新增附件：" + attachment);
                } else {
                    // 附件ID≠0 → 更新
                    sysNewsAttachmentMapper.updateSysNewsAttachmentById(attachment);
                    System.out.println("更新附件：" + attachment);
                    // 记录前端传递的非0附件ID
                    frontAttachmentIds.add(attachment.getAttachmentId());
                }
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