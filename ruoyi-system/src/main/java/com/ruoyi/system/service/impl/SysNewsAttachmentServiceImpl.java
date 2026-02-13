package com.ruoyi.system.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.SysNewsAttachment;
import com.ruoyi.system.mapper.SysNewsAttachmentMapper;
import com.ruoyi.system.service.ISysNewsAttachmentService;

/**
 * 新闻附件Service业务层处理
 * @author ruoyi
 */
@Service
public class SysNewsAttachmentServiceImpl implements ISysNewsAttachmentService {
    @Autowired
    private SysNewsAttachmentMapper sysNewsAttachmentMapper;

    // 若依文件上传根路径（读取application.yml配置）
    @Value("${ruoyi.profile}")
    private String profile;

    @Override
    public SysNewsAttachment uploadAndSaveAttachment(MultipartFile file, Long newsId, Integer sort) throws Exception {
        // 1. 若依文件上传（自动生成路径，支持图片/视频）
        String filePath = FileUploadUtils.upload(profile, file);

        // 2. 封装附件信息
        SysNewsAttachment attachment = new SysNewsAttachment();
        attachment.setNewsId(newsId);
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFilePath(filePath);
        attachment.setFileSize(file.getSize());
        attachment.setFileType(file.getContentType());
        // 提取文件后缀
        String fileName = file.getOriginalFilename();
        attachment.setFileExt(StringUtils.substringAfterLast(fileName, "."));
        attachment.setSort(sort == null ? 0 : sort);
        attachment.setStatus("0"); // 默认正常
        attachment.setCreateBy("admin"); // 若依获取当前登录用户：SecurityUtils.getUsername()

        // 3. 保存到数据库
        sysNewsAttachmentMapper.insertSysNewsAttachment(attachment);
        return attachment;
    }

    @Override
    public List<SysNewsAttachment> selectSysNewsAttachmentByNewsId(Long newsId) {
        return sysNewsAttachmentMapper.selectSysNewsAttachmentByNewsId(newsId);
    }

    @Override
    public int deleteSysNewsAttachmentByIds(Long[] attachmentIds) throws Exception {
     
        // 2. 删除数据库记录
        return sysNewsAttachmentMapper.deleteSysNewsAttachmentByIds(attachmentIds);
    }

    @Override
    public int updateSysNewsAttachmentSort(Long attachmentId, Integer sort) {
        return sysNewsAttachmentMapper.updateSysNewsAttachmentSort(attachmentId, sort);
    }
}