package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AppMember;
import org.apache.ibatis.annotations.Param;

public interface AppMemberMapper {
    /**
     * 根据邮箱查询会员
     */
    AppMember selectMemberByEmail(@Param("email") String email);

    /**
     * 新增会员（可选，用于首次登录自动注册）
     */
    int insertMember(AppMember member);

    AppMember selectMemberById(Long memberId);
}