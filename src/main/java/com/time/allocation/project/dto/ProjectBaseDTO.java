package com.time.allocation.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class ProjectBaseDTO {

    private Long id;

    @NotBlank
    @Size(max = 150)
    private String title;
    
    @NotBlank
    @Size(max = 300)
    private String description;
}
