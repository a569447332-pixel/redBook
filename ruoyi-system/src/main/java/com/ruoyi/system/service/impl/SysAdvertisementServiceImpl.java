// 实现类
package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysAdvertisementMapper;
import com.ruoyi.system.domain.SysAdvertisement;
import com.ruoyi.system.service.ISysAdvertisementService;
import com.ruoyi.common.core.text.Convert;

/**
 * 广告Service业务层处理
 *
 * @author ruoyi
 * @date 2026-02-11
 */
@Service
public class SysAdvertisementServiceImpl implements ISysAdvertisementService
{
    @Autowired
    private SysAdvertisementMapper sysAdvertisementMapper;

    /**
     * 查询广告
     *
     * @param adId 广告主键
     * @return 广告
     */
    @Override
    public SysAdvertisement selectSysAdvertisementByAdId(Long adId)
    {
        return sysAdvertisementMapper.selectSysAdvertisementByAdId(adId);
    }

    /**
     * 查询广告列表
     *
     * @param sysAdvertisement 广告
     * @return 广告
     */
    @Override
    public List<SysAdvertisement> selectSysAdvertisementList(SysAdvertisement sysAdvertisement)
    {
        return sysAdvertisementMapper.selectSysAdvertisementList(sysAdvertisement);
    }

    /**
     * 新增广告
     *
     * @param sysAdvertisement 广告
     * @return 结果
     */
    @Override
    public int insertSysAdvertisement(SysAdvertisement sysAdvertisement)
    {
        return sysAdvertisementMapper.insertSysAdvertisement(sysAdvertisement);
    }

    /**
     * 修改广告
     *
     * @param sysAdvertisement 广告
     * @return 结果
     */
    @Override
    public int updateSysAdvertisement(SysAdvertisement sysAdvertisement)
    {
        return sysAdvertisementMapper.updateSysAdvertisement(sysAdvertisement);
    }

    /**
     * 批量删除广告
     *
     * @param adIds 需要删除的广告主键
     * @return 结果
     */
    @Override
    public int deleteSysAdvertisementByAdIds(Long[] adIds)
    {
        return sysAdvertisementMapper.deleteSysAdvertisementByAdIds(adIds);
    }

    /**
     * 删除广告信息
     *
     * @param adId 广告主键
     * @return 结果
     */
    @Override
    public int deleteSysAdvertisementByAdId(Long adId)
    {
        return sysAdvertisementMapper.deleteSysAdvertisementByAdId(adId);
    }
}