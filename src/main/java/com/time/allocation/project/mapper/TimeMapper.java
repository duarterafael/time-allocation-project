package com.time.allocation.project.mapper;

import com.time.allocation.project.dto.TimeCompleteDTO;
import com.time.allocation.project.dto.TimeCreationDTO;
import com.time.allocation.project.dto.TimeSimpleDTO;
import com.time.allocation.project.entity.Time;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeMapper {

    private final ModelMapper modelMapper;

    public TimeMapper() {
        this.modelMapper = new ModelMapper();
    }

    public List<TimeSimpleDTO> toSimpleDTO(List<Time> times) {
        return times.stream().map(this::toSimpleDTO).collect(Collectors.toList());
    }

    public TimeSimpleDTO toSimpleDTO(Time time) {
        return modelMapper.map(time, TimeSimpleDTO.class);
    }

    public TimeCompleteDTO toCompleteDTO(Time time) {
        return modelMapper.map(time, TimeCompleteDTO.class);
    }

    public Time toEntity(TimeCreationDTO timeCreationDTO) {
        return modelMapper.map(timeCreationDTO, Time.class);
    }
}
