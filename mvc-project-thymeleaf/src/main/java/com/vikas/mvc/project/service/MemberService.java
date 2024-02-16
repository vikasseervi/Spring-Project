package com.vikas.mvc.project.service;

import com.vikas.mvc.project.entity.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Integer id);

    Member findByUsername(String username);

    void saveMember(Member member);

    void deleteMemberByUsername(String username);
}
