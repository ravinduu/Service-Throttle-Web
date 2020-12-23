package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.CustomerVehicleNotFound;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import com.servicethrottle.servicethrottlebackend.models.dto.CustomerVehicleDto;
import com.servicethrottle.servicethrottlebackend.repositories.CustomerVehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleService {

    private final CustomerService customerService;
    private final CustomerVehicleRepository customerVehicleRepository;


    public CustomerVehicle addCustomerVehicle(CustomerVehicleDto customerVehicleDto){
        CustomerVehicle customerVehicle = new CustomerVehicle();
        Customer currentCustomer = customerService.getCurrentCustomer();
        customerVehicle.setCustomer(currentCustomer);

        customerVehicleRepository.save(customerVehicle);
        return customerVehicle;
    }

    @Transactional(readOnly = true)
    public List<CustomerVehicle> getCustomerVehicles(String username) {
        Customer customer = customerService.getCustomer(username);
        return customerVehicleRepository
                .findAllByCustomer(customer)
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerVehicle> getAllCustomerVehicles() {
        return customerVehicleRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerVehicle getCustomerVehicle(Long id) {
        return customerVehicleRepository
                .findById(id)
                .orElseThrow(() -> new CustomerVehicleNotFound(id.toString()));
    }

    @Transactional(readOnly = true)
    public List<CustomerVehicle> getMyCustomerVehicles() {
        Customer currentCustomer = customerService.getCurrentCustomer();
        return customerVehicleRepository
                .findAllByCustomer(currentCustomer)
                .stream()
                .collect(Collectors.toList());
    }
}
