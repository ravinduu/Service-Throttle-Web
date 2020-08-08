package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
