package com.example.start.repository;

import com.example.start.domain.Members;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Members save(Members members);
    Optional<Members> findById(Long id);
    Optional<Members> findByName(String name);
    List<Members> findAll();
}
