package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 广告对象 sys_advertisement
 *
 * @author ruoyi
 * @date 2026-02-11
 */
public class SysAdvertisement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告ID */
    private Long adId;

    /** 广告名称 */
    private String adName;

    /** 分类ID */
    private Long categoryId;

    /** 广告图片路径 */
    private String adImage;

    /** 广告跳转链接 */
    private String adUrl;

    /** 排序 */
    private Integer sort;

    /** 状态（0正常 1停用） */
    private String status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    // 扩展：分类名称（用于列表展示）
    private String categoryName;

    public void setAdId(Long adId)
    {
        this.adId = adId;
    }

    public Long getAdId()
    {
        return adId;
    }
    public void setAdName(String adName)
    {
        this.adName = adName;
    }

    public String getAdName()
    {
        return adName;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setAdImage(String adImage)
    {
        this.adImage = adImage;
    }

    public String getAdImage()
    {
        return adImage;
    }
    public void setAdUrl(String adUrl)
    {
        this.adUrl = adUrl;
    }

    public String getAdUrl()
    {
        return adUrl;
    }
    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getSort()
    {
        return sort;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /** 备用跳转链接 */
    private String adBackupParams;

    // 新增getter/setter方法
    public String getAdBackupParams() {
        return adBackupParams;
    }

    public void setAdBackupParams(String adBackupParams) {
        this.adBackupParams = adBackupParams;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("adId", getAdId())
                .append("adName", getAdName())
                .append("categoryId", getCategoryId())
                .append("adImage", getAdImage())
                .append("adUrl", getAdUrl())
                .append("adBackupParams", getAdBackupParams()) // 新增字段
                .append("sort", getSort())
                .append("status", getStatus())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}