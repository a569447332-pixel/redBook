package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysNewsAttachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * 新闻附件Service接口
 * @author ruoyi
 */
public interface ISysNewsAttachmentService {
    /**
     * 上传并保存新闻附件（整合若依文件上传）
     * @param file 上传的文件
     * @param newsId 新闻ID
     * @param sort 排序值
     * @return 附件对象
     * @throws Exception 上传异常
     */
    SysNewsAttachment uploadAndSaveAttachment(MultipartFile file, Long newsId, Integer sort) throws Exception;

    /**
     * 根据新闻ID查询附件列表
     * @param newsId 新闻ID
     * @return 附件列表
     */
    List<SysNewsAttachment> selectSysNewsAttachmentByNewsId(Long newsId);

    /**
     * 批量删除新闻附件（含物理文件删除）
     * @param attachmentIds 附件ID数组
     * @return 结果
     * @throws Exception 删除异常
     */
    int deleteSysNewsAttachmentByIds(Long[] attachmentIds) throws Exception;

    /**
     * 修改附件排序
     * @param attachmentId 附件ID
     * @param sort 排序值
     * @return 结果
     */
    int updateSysNewsAttachmentSort(Long attachmentId, Integer sort);
}