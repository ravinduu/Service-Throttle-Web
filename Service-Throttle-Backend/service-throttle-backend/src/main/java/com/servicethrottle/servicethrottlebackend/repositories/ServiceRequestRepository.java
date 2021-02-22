package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import com.servicethrottle.servicethrottlebackend.models.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByCustomer(Customer customer);

    List<ServiceRequest> findByCustomerVehicle(CustomerVehicle customerVehicle);
}
