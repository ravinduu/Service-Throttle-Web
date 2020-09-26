package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.PasswordResetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetKey, Long> {

    public Optional<PasswordResetKey> findOneByResetKey(String resetKey);
}
