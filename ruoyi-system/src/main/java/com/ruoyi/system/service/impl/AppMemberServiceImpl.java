package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.AppMember;
import com.ruoyi.system.mapper.AppMemberMapper;
import com.ruoyi.system.service.IAppMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppMemberServiceImpl implements IAppMemberService {

    @Autowired
    private AppMemberMapper appMemberMapper;

    @Override
    public AppMember selectMemberByEmail(String email) {
        return appMemberMapper.selectMemberByEmail(email);
    }

    @Override
    public int insertMember(AppMember member) {
        return appMemberMapper.insertMember(member);
    }

    @Override
    public AppMember selectMemberById(Long memberId) {
        return appMemberMapper.selectMemberById(memberId);
    }
}