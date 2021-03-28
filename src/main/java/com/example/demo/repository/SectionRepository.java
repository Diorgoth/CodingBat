package com.example.demo.repository;

import com.example.demo.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);


}
