package com.example.demo.dataBases.repositories;

import com.example.demo.dataBases.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {


   @Query("SELECT a FROM UserEntity a WHERE a.superName like %?1%")
   List<UserEntity> findBySuperName(String name, PageRequest pageable);

}
