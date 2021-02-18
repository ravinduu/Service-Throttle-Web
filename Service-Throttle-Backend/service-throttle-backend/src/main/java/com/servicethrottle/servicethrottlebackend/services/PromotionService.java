package com.servicethrottle.servicethrottlebackend.services;


import com.servicethrottle.servicethrottlebackend.exceptions.ValueNotFoundException;
import com.servicethrottle.servicethrottlebackend.models.Promotion;
import com.servicethrottle.servicethrottlebackend.models.dto.PromotionDto;
import com.servicethrottle.servicethrottlebackend.repositories.PromotionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public Promotion addPromotion(PromotionDto promotionDto) {
        Promotion promotion = new Promotion();
        return addDataAndSavePromotion(promotion, promotionDto);
    }

    private Promotion addDataAndSavePromotion(Promotion promotion, PromotionDto promotionDto) {
        promotion.setPromoCode(promotionDto.getPromoCode());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setPromoDescription(promotionDto.getPromoDescription());
        promotion.setImageUri(promotionDto.getImageUri());
        promotion.setCreatedAt(LocalDate.now());
        promotion.setEndAt(LocalDate.now().plus(7, ChronoUnit.DAYS));
        promotionRepository.save(promotion);
        return promotion;
    }

    public List<Promotion> getAllPromotions() {
        return new ArrayList<>(promotionRepository.findAll());
    }

    public Promotion getAPromotion(long id) {
        return promotionRepository.findById(id).orElseThrow(() -> new ValueNotFoundException("No Such Promotion to be found by id : "+id));
    }

    public Promotion updatePromotion(long id, PromotionDto promotionDto) {
        Promotion promotion = getAPromotion(id);
        return addDataAndSavePromotion(promotion, promotionDto);
    }

    public int deletePromotion(long id) {
        Promotion promotion = getAPromotion(id);
        if (promotion != null){
            promotionRepository.delete(promotion);
            return 1;
        }
        return 0;
    }
}
