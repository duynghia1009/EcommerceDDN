package com.DDN.login.repository;

import com.DDN.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
public interface UserReposity extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT id, username, first_name, last_name, email, password " +
            "FROM user where email = :EMAIL",
            nativeQuery = true)
    Optional<User> findByEmail(@Param("EMAIL") String EMAIL);

    @Query(value = "DELETE FROM user where username = :USERNAME and password = :PASSWORD",
           nativeQuery = true)
    void deleteByUsernamePassword(@Param("USERNAME") String USERNAME, @Param("PASSWORD") String PASSWORD);



}
