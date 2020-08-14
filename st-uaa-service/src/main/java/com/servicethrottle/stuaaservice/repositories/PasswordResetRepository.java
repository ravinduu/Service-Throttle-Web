package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.PasswordResetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetKey, Long> {
}
