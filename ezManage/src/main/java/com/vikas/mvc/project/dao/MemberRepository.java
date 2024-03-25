package com.vikas.mvc.project.dao;

import com.vikas.mvc.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
