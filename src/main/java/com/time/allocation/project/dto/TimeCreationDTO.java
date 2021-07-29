package com.time.allocation.project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TimeCreationDTO extends TimeBaseDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long projectId;
}
