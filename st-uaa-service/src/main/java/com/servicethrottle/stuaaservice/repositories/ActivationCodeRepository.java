package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationCodeRepository extends JpaRepository<ActivationCode,Long> {

    Optional<ActivationCode> findByActivationCode(String code);
}