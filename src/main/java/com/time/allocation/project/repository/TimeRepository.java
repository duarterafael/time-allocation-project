package com.time.allocation.project.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.time.allocation.project.entity.Time;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

    @EntityGraph(attributePaths = {"user", "project"})
    @Override
    List<Time> findAll();

    @EntityGraph(attributePaths = {"user", "project"})
    @Override
    Optional<Time> findById(Long id);
    
    @EntityGraph(attributePaths = {"user", "project"})
    List<Time> findByUserId(Long userId);
    
    @EntityGraph(attributePaths = {"user", "project"})
    List<Time> findByProjectId(Long projectId);

}
