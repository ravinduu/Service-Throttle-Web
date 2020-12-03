package com.servicethrottle.servicethrottlebackend.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * DTO for Authentication Response - jwt token, username, refresh token and expire date of jwt token
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {

    private String JWTToken;
    private String username;
    private String refreshToken;
    private Instant expireAt;

}
