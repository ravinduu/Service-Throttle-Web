package com.servicethrottle.servicethrottlebackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    @NotNull
    private String promoCode;

    @NotNull
    @Min(0)
    private double discount;

    @NotNull
    private String promoDescription;

    private String imageUri;
}
