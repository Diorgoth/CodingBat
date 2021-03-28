package com.example.demo.repository;

import com.example.demo.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {

boolean existsByTitle(String title);

boolean existsByTitleAndIdNot(String title, Integer id);

}
