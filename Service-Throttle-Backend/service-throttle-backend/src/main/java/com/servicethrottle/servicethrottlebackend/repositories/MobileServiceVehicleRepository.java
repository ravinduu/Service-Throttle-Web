package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.MobileMechanic;
import com.servicethrottle.servicethrottlebackend.models.MobileServiceVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileServiceVehicleRepository extends JpaRepository<MobileServiceVehicle, Long> {
    Optional<MobileServiceVehicle> findByMobileMechanic(MobileMechanic mobileMechanic);
}
