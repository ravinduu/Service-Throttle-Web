package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.exceptions.NoStVehicleService;
import com.servicethrottle.servicethrottlebackend.exceptions.ValueNotFoundException;
import com.servicethrottle.servicethrottlebackend.models.Promotion;
import com.servicethrottle.servicethrottlebackend.models.STVehicleService;
import com.servicethrottle.servicethrottlebackend.models.dto.PromotionDto;
import com.servicethrottle.servicethrottlebackend.models.dto.STVehicleServiceDto;
import com.servicethrottle.servicethrottlebackend.services.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/st/promotion")
@AllArgsConstructor
@CrossOrigin("*")
public class PromotionController {

    private final PromotionService promotionService;

    /**
     * add a service
     *
     * only for ADMIN
     * parameter {@link PromotionDto} is the information about the promotion
     * return {@link Promotion}
     * */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Promotion addPromotion(@RequestBody PromotionDto promotionDto){
        System.out.println(promotionDto);
    return promotionService.addPromotion(promotionDto);
    }

    /**
     * get all promotions available
     *
     * return List of {@link Promotion} if available or empty List
     * */
    @GetMapping("/all")
    public List<Promotion> getAllPromotions(){
        return promotionService.getAllPromotions();
    }

    /**
     * get a promotion available by its id
     *
     * PathVariable id is the id of the promotion we want
     * return a {@link PromotionDto}
     * throw {@link ValueNotFoundException} if error occur
     * */
    @GetMapping("/{id}")
    public Promotion getAPromotion(@PathVariable long id){
        return promotionService.getAPromotion(id);
    }

    /**
     * update a promotion available by its id
     *
     * only for ADMIN
     * PathVariable id is the id of the service we want to update
     * RequestBody {@link PromotionDto} is the new information about the promotion
     * return a {@link Promotion}
     * throw {@link ValueNotFoundException} if error occur
     * */
    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Promotion updatePromotion(@PathVariable long id, @RequestBody PromotionDto promotionDto){
        return promotionService.updatePromotion(id, promotionDto);
    }

    /**
     * delete a services available by its id
     *
     * only for ADMIN
     * PathVariable id is the id of the service we want to delete
     * return 1 if the process is success or 0 if error
     * */
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public int deletePromotion(@PathVariable long id){
        return promotionService.deletePromotion(id);
    }
}
