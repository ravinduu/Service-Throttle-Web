package com.servicethrottle.servicethrottlebackend.services;


import com.servicethrottle.servicethrottlebackend.exceptions.NoStVehicleService;
import com.servicethrottle.servicethrottlebackend.models.STVehicleService;
import com.servicethrottle.servicethrottlebackend.models.dto.STVehicleServiceDto;
import com.servicethrottle.servicethrottlebackend.repositories.STVehicleServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class STVehicleServiceService {

    private final STVehicleServiceRepository stVehicleServiceRepository;

    public STVehicleService addVehicleService(STVehicleServiceDto STVehicleServiceDto) {

        STVehicleService STVehicleService = new STVehicleService();
        return addDataAndSaveVehicleService(STVehicleService, STVehicleServiceDto);
    }

    private STVehicleService addDataAndSaveVehicleService(STVehicleService STVehicleService, STVehicleServiceDto STVehicleServiceDto) {
        STVehicleService.setVehicleServiceName(STVehicleServiceDto.getVehicleServiceName());
        STVehicleService.setVehicleServicePrice(STVehicleServiceDto.getVehicleServicePrice());
        STVehicleService.setVehicleServiceDescription(STVehicleServiceDto.getVehicleServiceDescription());
        STVehicleService.setVehicleServiceType(STVehicleServiceDto.getVehicleServiceType());
        stVehicleServiceRepository.save(STVehicleService);
        return STVehicleService;
    }


    public List<STVehicleService> getAllStVehicleService() {
        return new ArrayList<>(stVehicleServiceRepository.findAll());
    }

    public STVehicleService getAStVehicleService(long id) {
        return stVehicleServiceRepository.findById(id).orElseThrow(()-> new NoStVehicleService("No Service To Find By id : "+id));
    }

    public STVehicleService updateStVehicleService(long id, STVehicleServiceDto stVehicleServiceDto) {
        STVehicleService stVehicleService = getAStVehicleService(id);
        return addDataAndSaveVehicleService(stVehicleService, stVehicleServiceDto);
    }

    public int deleteStVehicleService(long id) {
        STVehicleService stVehicleService = getAStVehicleService(id);
        if (stVehicleService != null){
            stVehicleServiceRepository.delete(stVehicleService);
            return 1;
        }
        return 0;
    }
}
