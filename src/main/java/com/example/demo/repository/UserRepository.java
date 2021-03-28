package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByUsernameAndEmail(String username, String email);
    boolean existsByUsernameAndEmailAndIdNot(String username, String email, Integer id);
}
