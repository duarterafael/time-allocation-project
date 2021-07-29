package com.time.allocation.project.mapper;

import com.time.allocation.project.dto.UserCompleteDTO;
import com.time.allocation.project.dto.UserCreationDTO;
import com.time.allocation.project.dto.UserSimpleDTO;
import com.time.allocation.project.entity.User;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
    }

    public List<UserSimpleDTO> toSimpleDTO(List<User> users) {
        return users.stream().map(this::toSimpleDTO).collect(Collectors.toList());
    }

    public UserSimpleDTO toSimpleDTO(User user) {
        return modelMapper.map(user, UserSimpleDTO.class);
    }

    public UserCompleteDTO toCompleteDTO(User user) {
        return modelMapper.map(user, UserCompleteDTO.class);
    }

    public User toEntity(UserCreationDTO userCreationDTO) {
        return modelMapper.map(userCreationDTO, User.class);
    }
}
