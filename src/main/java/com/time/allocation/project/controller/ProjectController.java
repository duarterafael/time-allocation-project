package com.time.allocation.project.controller;

import com.time.allocation.project.dto.ErrorDTO;
import com.time.allocation.project.dto.ProjectCompleteDTO;
import com.time.allocation.project.dto.ProjectCreationDTO;
import com.time.allocation.project.dto.ProjectSimpleDTO;
import com.time.allocation.project.entity.Project;
import com.time.allocation.project.mapper.ProjectMapper;
import com.time.allocation.project.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"/projects"})
@RestController
@RequestMapping(path = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper mapper;

    public ProjectController(ProjectService projectService, ProjectMapper mapper) {
        super();
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Find all projects")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProjectSimpleDTO>> findAll(@RequestParam(name = "title", required = false) String title) {
        List<Project> projects = projectService.findAll(title);
        return new ResponseEntity<>(mapper.toSimpleDTO(projects), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a project by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @GetMapping(path = "/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProjectCompleteDTO> findById(@PathVariable(name = "project_id") Long id) {
        Project project = projectService.findById(id);
        return new ResponseEntity<>(mapper.toCompleteDTO(project), HttpStatus.OK);
    }

    @ApiOperation(value = "Save a project")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjectSimpleDTO> save(@Valid @RequestBody ProjectCreationDTO projectDTO) {
        Project project = projectService.save(mapper.toEntity(projectDTO));
        return new ResponseEntity<>(mapper.toSimpleDTO(project), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @PutMapping(path = "/{project_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProjectSimpleDTO> update(@PathVariable(name = "project_id") Long id,
                                                  @Valid @RequestBody ProjectCreationDTO projectDTO) {
        projectDTO.setId(id);
        Project project = projectService.update(mapper.toEntity(projectDTO));
        return new ResponseEntity<>(mapper.toSimpleDTO(project), HttpStatus.OK);
    }

}
