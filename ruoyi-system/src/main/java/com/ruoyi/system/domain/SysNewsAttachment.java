package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import java.util.Date;

/**
 * 新闻附件实体类
 * @author ruoyi
 */
@Data
@TableName("sys_news_attachment")
public class SysNewsAttachment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    @TableId(type = IdType.AUTO)
    private Long attachmentId;

    /** 关联新闻ID */
    private Long newsId;

    /** 附件原始名称 */
    private String fileName;

    /** 附件存储路径 */
    private String filePath;

    /** 文件大小（字节） */
    private Long fileSize;

    /** 文件类型（image/jpg、video/mp4等） */
    private String fileType;

    /** 文件后缀 */
    private String fileExt;

    /** 排序 */
    private Integer sort;

    /** 状态（0正常 1禁用） */
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}