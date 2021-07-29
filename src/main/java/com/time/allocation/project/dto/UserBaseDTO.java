package com.time.allocation.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class UserBaseDTO {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotEmpty(message = "Email is mandatory.")
    @Email(message = "Email be valid")
    private String email;
    
    @NotBlank
    @Size(max = 15)
    private String login;


}
