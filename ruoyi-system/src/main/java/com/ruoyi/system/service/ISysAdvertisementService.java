// 接口
package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysAdvertisement;

/**
 * 广告Service接口
 *
 * @author ruoyi
 * @date 2026-02-11
 */
public interface ISysAdvertisementService
{
    /**
     * 查询广告
     *
     * @param adId 广告主键
     * @return 广告
     */
    public SysAdvertisement selectSysAdvertisementByAdId(Long adId);

    /**
     * 查询广告列表
     *
     * @param sysAdvertisement 广告
     * @return 广告集合
     */
    public List<SysAdvertisement> selectSysAdvertisementList(SysAdvertisement sysAdvertisement);

    /**
     * 新增广告
     *
     * @param sysAdvertisement 广告
     * @return 结果
     */
    public int insertSysAdvertisement(SysAdvertisement sysAdvertisement);

    /**
     * 修改广告
     *
     * @param sysAdvertisement 广告
     * @return 结果
     */
    public int updateSysAdvertisement(SysAdvertisement sysAdvertisement);

    /**
     * 批量删除广告
     *
     * @param adIds 需要删除的广告主键集合
     * @return 结果
     */
    public int deleteSysAdvertisementByAdIds(Long[] adIds);

    /**
     * 删除广告信息
     *
     * @param adId 广告主键
     * @return 结果
     */
    public int deleteSysAdvertisementByAdId(Long adId);
}
