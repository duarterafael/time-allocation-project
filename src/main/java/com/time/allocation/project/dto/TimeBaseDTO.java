package com.time.allocation.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Data
public class TimeBaseDTO {

    private Long id;
    
    @NotNull
    @ApiModelProperty(example = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date startAt;

    @NotNull
    @ApiModelProperty(example = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date endedAt;
}
