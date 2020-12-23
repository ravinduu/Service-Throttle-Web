package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerVehicleRepository extends JpaRepository<CustomerVehicle, Long> {

    List<CustomerVehicle> findAllByCustomer(Customer customer);
}
