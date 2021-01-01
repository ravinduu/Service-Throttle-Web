package com.servicethrottle.servicethrottlebackend.models.dto;

import com.servicethrottle.servicethrottlebackend.models.enums.Engine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEngineDto {
    private Engine engine;
}
