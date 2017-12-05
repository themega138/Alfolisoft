/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services;

import com.mq310.ent.persons.Member;
import java.util.SortedSet;

/**
 *
 * @author Moises
 */
public interface IMemberService {
    
    public abstract Member saveMember(Member member);
    
    public abstract SortedSet<Member> getMemberList();
    
    public abstract Long getMembersCount();
    
}
