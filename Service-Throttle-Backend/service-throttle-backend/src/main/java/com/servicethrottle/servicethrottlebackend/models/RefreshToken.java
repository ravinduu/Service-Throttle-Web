package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Refresh Token - use for invalidate jwt token
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "st_refresh_token")
public class RefreshToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Instant createdDate;
}
