package com.time.allocation.project.controller;

import com.time.allocation.project.dto.ErrorDTO;
import com.time.allocation.project.dto.UserCompleteDTO;
import com.time.allocation.project.dto.UserCreationDTO;
import com.time.allocation.project.dto.UserSimpleDTO;
import com.time.allocation.project.entity.User;
import com.time.allocation.project.mapper.UserMapper;
import com.time.allocation.project.service.UserService;

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

@Api(tags = {"/users"})
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        super();
        this.userService = userService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Find all Users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserSimpleDTO>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<User> users = userService.findAll(name);
        return new ResponseEntity<>(mapper.toSimpleDTO(users), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @GetMapping(path = "/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserCompleteDTO> findById(@PathVariable(name = "user_id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(mapper.toCompleteDTO(user), HttpStatus.OK);
    }
  
    @ApiOperation(value = "Save a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserSimpleDTO> save(@Valid @RequestBody UserCreationDTO userDTO) {
        User user = userService.save(mapper.toEntity(userDTO));
        return new ResponseEntity<>(mapper.toSimpleDTO(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)
    })
    @PutMapping(path = "/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserSimpleDTO> update(@PathVariable(name = "user_id") Long id,
                                                     @Valid @RequestBody UserCreationDTO userDTO) {
        userDTO.setId(id);
        User user = userService.update(mapper.toEntity(userDTO));
        return new ResponseEntity<>(mapper.toSimpleDTO(user), HttpStatus.OK);
    }
   
}
