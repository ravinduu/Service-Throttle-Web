package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RefreshToken entity.
 */

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

}
