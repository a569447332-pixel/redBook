package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysNewsAttachment;
import com.ruoyi.system.service.ISysNewsAttachmentService;

/**
 * 新闻附件Controller
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/news/attachment")
public class SysNewsAttachmentController extends BaseController {
    @Autowired
    private ISysNewsAttachmentService sysNewsAttachmentService;

    /**
     * 上传新闻附件（支持图片/视频）
     */
    @PostMapping("/upload")
    public AjaxResult uploadAttachment(@RequestParam("file") MultipartFile file,
                                       @RequestParam("newsId") Long newsId,
                                       @RequestParam(value = "sort", defaultValue = "0") Integer sort) {
        try {
            SysNewsAttachment attachment = sysNewsAttachmentService.uploadAndSaveAttachment(file, newsId, sort);
            return AjaxResult.success("上传成功", attachment);
        } catch (Exception e) {
            return AjaxResult.error("上传失败：" + e.getMessage());
        }
    }

    /**
     * 根据新闻ID查询附件列表
     */
    @GetMapping("/list")
    public AjaxResult getAttachmentList(@RequestParam("newsId") Long newsId) {
        List<SysNewsAttachment> list = sysNewsAttachmentService.selectSysNewsAttachmentByNewsId(newsId);
        return AjaxResult.success(list);
    }

    /**
     * 批量删除新闻附件
     */
    @PostMapping("/delete")
    public AjaxResult deleteAttachment(@RequestParam("attachmentIds") Long[] attachmentIds) {
        try {
            int rows = sysNewsAttachmentService.deleteSysNewsAttachmentByIds(attachmentIds);
            return toAjax(rows);
        } catch (Exception e) {
            return AjaxResult.error("删除失败：" + e.getMessage());
        }
    }

    /**
     * 修改附件排序
     */
    @PostMapping("/updateSort")
    public AjaxResult updateSort(@RequestParam("attachmentId") Long attachmentId,
                                 @RequestParam("sort") Integer sort) {
        return toAjax(sysNewsAttachmentService.updateSysNewsAttachmentSort(attachmentId, sort));
    }
}