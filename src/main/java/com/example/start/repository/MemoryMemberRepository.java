package com.example.start.repository;

import com.example.start.domain.Members;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Members> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Members save(Members members) {
        members.setId(++sequence);
        store.put(members.getId(), members);
        return members;
    }
    @Override
    public Optional<Members> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<Members> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Members> findByName(String name) {
        return store.values().stream()
                .filter(members -> members.getName().equals(name))
                .findAny();
    }
    public void clearStore() {
        store.clear();
    }
}
