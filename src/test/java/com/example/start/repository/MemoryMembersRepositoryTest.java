package com.example.start.repository;

import com.example.start.domain.Members;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMembersRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //given
        Members members = new Members();
        members.setName("spring");

        //when
        repository.save(members);

        //then
        Members result = repository.findById(members.getId()).get();
        assertThat(result).isEqualTo(members);
    }

    @Test
    public void findByName(){
        Members members1 = new Members();
        members1.setName("spring1");
        repository.save(members1);

        Members members2 = new Members();
        members2.setName("spring2");
        repository.save(members2);

        //when
        Members result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(members1);
    }

    @Test
    public void findAll(){
        Members members1 = new Members();
        members1.setName("spring1");
        repository.save(members1);

        Members members2 = new Members();
        members2.setName("spring2");
        repository.save(members2);

        List<Members> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
