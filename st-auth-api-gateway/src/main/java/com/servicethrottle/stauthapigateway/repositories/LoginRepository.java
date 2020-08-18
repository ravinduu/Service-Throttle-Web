package com.servicethrottle.stauthapigateway.repositories;

import com.servicethrottle.stauthapigateway.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {
    Optional<Login> findOneByUsername(String username);
}
