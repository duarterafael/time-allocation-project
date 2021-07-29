package com.time.allocation.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCreationDTO extends UserBaseDTO {
	
    
    @NotBlank
    @Size(max = 25)
    private String password;

}
