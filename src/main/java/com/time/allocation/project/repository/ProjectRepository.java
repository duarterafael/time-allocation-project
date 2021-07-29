package com.time.allocation.project.repository;

import com.time.allocation.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByTitleContainingIgnoreCase(String title);
}
