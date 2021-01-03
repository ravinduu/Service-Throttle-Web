package com.servicethrottle.servicethrottlebackend.models;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "st_promotion_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String promoCode;

    @NotNull
    @Min(0)
    private double discount;

    @NotNull
    private String promoDescription;

    private Instant createdAt;

    private Instant endAt;

}
