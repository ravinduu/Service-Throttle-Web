package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findOneByEmail(String email);
}
