package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findOneByUsername(String username);
}
