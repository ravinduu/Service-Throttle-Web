package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
