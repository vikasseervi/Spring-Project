package com.vikas.mvc.project.dao;

import com.vikas.mvc.project.entity.Member;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByUsername(String username);

    Member deleteByUsername(String username);
}
