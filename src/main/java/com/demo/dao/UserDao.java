package com.demo.dao;

import com.demo.POJO.User;
import com.demo.wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao  extends JpaRepository<User, Integer> {


    User findByEmailId(@Param("email")String email);

    List<UserWrapper> getAllUser();

    List<String> getAllAdmin();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status")String status,@Param("id")Integer id);

    User findByEmail(String email);


}
