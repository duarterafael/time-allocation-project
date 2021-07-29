package com.time.allocation.project.mapper;

import com.time.allocation.project.dto.ProjectCompleteDTO;
import com.time.allocation.project.dto.ProjectCreationDTO;
import com.time.allocation.project.dto.ProjectSimpleDTO;
import com.time.allocation.project.entity.Project;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    private final ModelMapper modelMapper;

    public ProjectMapper() {
        this.modelMapper = new ModelMapper();
    }

    public List<ProjectSimpleDTO> toSimpleDTO(List<Project> project) {
        return project.stream().map(this::toSimpleDTO).collect(Collectors.toList());
    }

    public ProjectSimpleDTO toSimpleDTO(Project project) {
        return modelMapper.map(project, ProjectSimpleDTO.class);
    }

    public ProjectCompleteDTO toCompleteDTO(Project project) {
        return modelMapper.map(project, ProjectCompleteDTO.class);
    }

    public Project toEntity(ProjectCreationDTO projectCreationDTO) {
        return modelMapper.map(projectCreationDTO, Project.class);
    }
}
