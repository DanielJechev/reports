package com.example.demo.repository;

import com.example.demo.model.entity.ReportDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDefinitionRepository extends JpaRepository<ReportDefinition, Integer> {
}
