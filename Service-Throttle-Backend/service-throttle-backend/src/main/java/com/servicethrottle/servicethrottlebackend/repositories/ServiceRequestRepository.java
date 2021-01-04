package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
}
