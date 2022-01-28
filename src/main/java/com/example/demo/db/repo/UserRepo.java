package com.example.demo.db.repo;

import com.example.demo.db.ent.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findBySuperName(String s);

}
