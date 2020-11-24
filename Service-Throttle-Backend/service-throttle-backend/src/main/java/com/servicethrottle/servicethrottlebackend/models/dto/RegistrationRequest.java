package com.servicethrottle.servicethrottlebackend.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotNull
    @Size(min = 5, max = 100)
    private String username = "customerByREG";

    @JsonIgnore
    @NotNull
    @Size(min = 8)
    private String password = "customer";

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String email = "customer";

    @NotNull
    private String accountType = "customerAccount";


}
