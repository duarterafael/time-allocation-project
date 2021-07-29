package com.time.allocation.project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TimeSimpleDTO extends TimeBaseDTO {

    private UserSimpleDTO user;

    private ProjectSimpleDTO project;
}
