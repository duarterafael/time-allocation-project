package com.time.allocation.project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCompleteDTO extends UserSimpleDTO {

    private List<TimeSimpleDTO> times;
}
