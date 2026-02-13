package com.ruoyi.system.service;

import com.ruoyi.system.domain.AppMember;

public interface IAppMemberService {
    AppMember selectMemberByEmail(String email);
    int insertMember(AppMember member);

    // 新增：根据ID查询会员
    AppMember selectMemberById(Long memberId);
}