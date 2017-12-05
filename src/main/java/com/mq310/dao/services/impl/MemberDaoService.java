/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services.impl;

import com.mq310.dao.services.IMemberService;
import com.mq310.ent.persons.Member;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class MemberDaoService extends GeneralService implements IMemberService{

    @Override
    @Transactional
    public Member saveMember(Member member) {
        Member m = member;
        m.setId(dao.save(m));
        return m;
    }

    @Override
    @Transactional
    public SortedSet<Member> getMemberList() {
        return new TreeSet<>(dao.getAll(Member.class));
    }

    @Override
    public Long getMembersCount() {
        return dao.get(Long.class, "select count(m.id) from Member as m");
    }
    
}
