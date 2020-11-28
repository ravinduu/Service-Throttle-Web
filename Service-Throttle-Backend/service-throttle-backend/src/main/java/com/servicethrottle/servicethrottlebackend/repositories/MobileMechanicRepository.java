package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.MobileMechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileMechanicRepository extends JpaRepository<MobileMechanic, Long> {
    Optional<MobileMechanic> findOneByEmail(String email);

    Optional<MobileMechanic> findOneByUsername(String username);
}
