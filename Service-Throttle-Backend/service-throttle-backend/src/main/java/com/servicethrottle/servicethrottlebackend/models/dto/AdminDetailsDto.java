package com.servicethrottle.servicethrottlebackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetailsDto extends UserDetailsDto {

    private boolean isSuperAdmin = false;

}
