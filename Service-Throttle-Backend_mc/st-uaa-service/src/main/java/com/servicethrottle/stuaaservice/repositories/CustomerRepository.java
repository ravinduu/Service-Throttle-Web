package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional <Customer> findOneByCustUsername(String custUsername);

    Optional<Customer> findOneByCustEmail(String custEmail);
}
