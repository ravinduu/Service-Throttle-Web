package com.servicethrottle.servicethrottlebackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileMechanicDetailsDto extends SupervisorDetailsDto{
    private boolean isAssignToTask = false;

}
