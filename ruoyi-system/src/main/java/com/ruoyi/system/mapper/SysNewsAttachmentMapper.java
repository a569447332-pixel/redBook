package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysNewsAttachment;
import org.apache.ibatis.annotations.Param;

/**
 * 新闻附件Mapper接口
 * @author ruoyi
 */
public interface SysNewsAttachmentMapper {
    /**
     * 新增新闻附件
     * @param sysNewsAttachment 新闻附件对象
     * @return 结果
     */
    int insertSysNewsAttachment(SysNewsAttachment sysNewsAttachment);

    /**
     * 根据新闻ID查询附件列表
     * @param newsId 新闻ID
     * @return 附件列表
     */
    List<SysNewsAttachment> selectSysNewsAttachmentByNewsId(@Param("newsId") Long newsId);

    /**
     * 批量删除新闻附件
     * @param attachmentIds 需要删除的附件ID数组
     * @return 结果
     */
    int deleteSysNewsAttachmentByIds(Long[] attachmentIds);

    /**
     * 修改附件排序
     * @param attachmentId 附件ID
     * @param sort 排序值
     * @return 结果
     */
    int updateSysNewsAttachmentSort(@Param("attachmentId") Long attachmentId, @Param("sort") Integer sort);
}