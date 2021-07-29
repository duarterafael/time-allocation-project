package com.time.allocation.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.time.allocation.project.dto.TimeCompleteDTO;
import com.time.allocation.project.dto.TimeCreationDTO;
import com.time.allocation.project.dto.TimeSimpleDTO;
import com.time.allocation.project.dto.ErrorDTO;
import com.time.allocation.project.entity.Time;
import com.time.allocation.project.mapper.TimeMapper;
import com.time.allocation.project.service.TimeService;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"/times"})
@RestController
@RequestMapping(path = "/times", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeController {

    private final TimeService timeService;
    private final TimeMapper mapper;

    public TimeController(TimeService timeService, TimeMapper mapper) {
        super();
        this.timeService = timeService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Find all times")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TimeSimpleDTO>> findAll() {
        List<Time> times = timeService.findAll();
        return new ResponseEntity<>(mapper.toSimpleDTO(times), HttpStatus.OK);
    }

    @ApiOperation(value = "Find an time by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @GetMapping(path = "/{time_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TimeCompleteDTO> findById(@PathVariable(name = "time_id") Long id) {
        Time time = timeService.findById(id);
        return new ResponseEntity<>(mapper.toCompleteDTO(time), HttpStatus.OK);
    }

    @ApiOperation(value = "Find times by user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @GetMapping(path = "/user/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TimeSimpleDTO>> findByUser(@PathVariable(name = "user_id") Long id) {
        List<Time> times = timeService.findByUser(id);
        return new ResponseEntity<>(mapper.toSimpleDTO(times), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Find times by project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @GetMapping(path = "/project/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TimeSimpleDTO>> findByProject(@PathVariable(name = "project_id") Long id) {
        List<Time> times = timeService.findByProject(id);
        return new ResponseEntity<>(mapper.toSimpleDTO(times), HttpStatus.OK);
    }

    @ApiOperation(value = "Save a time")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TimeSimpleDTO> save(@Valid @RequestBody TimeCreationDTO timeDTO) {
        Time time = timeService.save(mapper.toEntity(timeDTO));
        return new ResponseEntity<>(mapper.toSimpleDTO(time), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an time")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @PutMapping(path = "/{time_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TimeSimpleDTO> update(@PathVariable(name = "time_id") Long id,
                                                      @Valid @RequestBody TimeCreationDTO timeDTO) {
        timeDTO.setId(id);
        Time time = timeService.update(mapper.toEntity(timeDTO));
        return new ResponseEntity<>(mapper.toSimpleDTO(time), HttpStatus.OK);
    }

}
