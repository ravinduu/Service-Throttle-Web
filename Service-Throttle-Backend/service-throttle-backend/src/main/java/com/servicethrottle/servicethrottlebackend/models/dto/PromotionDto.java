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
    private String promoCode;

    private double discount;

    private String promoDescription;

    private String imageUri;
}
