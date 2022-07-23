package com.demo.supportportal.repository;

import com.demo.supportportal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUsername(String username);

    public User findUserByEmail(String email);
}
