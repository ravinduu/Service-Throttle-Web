package com.servicethrottle.servicethrottlebackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for reset password - old and new passwords
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDto {
    @NotNull
    @Size(min = 8)
    private String oldPassword;

    @NotNull
    @Size(min = 8)
    private String newPassword;
}
