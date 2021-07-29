package com.time.allocation.project.service;

import com.time.allocation.project.exception.EntityInstanceNotFoundException;
import com.time.allocation.project.entity.Project;
import com.time.allocation.project.repository.ProjectRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Transactional
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository courseRepository) {
        super();
        this.projectRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<Project> findAll(String title) {
        if (title == null) {
            return projectRepository.findAll();
        } else {
            return projectRepository.findByTitleContainingIgnoreCase(title);
        }
    }

    @Transactional(readOnly = true)
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(getEntityInstanceNotFoundExceptionSupplier(id));
    }

    public Project save(Project project) {
        project.setId(null);
        return projectRepository.save(project);
    }

    public Project update(Project project) {
        Long id = project.getId();
        if (id == null || !projectRepository.existsById(id)) {
            throw getEntityInstanceNotFoundExceptionSupplier(id).get();
        }

        return projectRepository.save(project);
    }
   
    private Supplier<EntityInstanceNotFoundException> getEntityInstanceNotFoundExceptionSupplier(Long id) {
        return () -> new EntityInstanceNotFoundException(Project.class, id);
    }
}
