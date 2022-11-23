package com.example.demojstreeproject.repository;

import com.example.demojstreeproject.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity,Long> {
    DemoEntity findByEntityName(String entityName);
}
