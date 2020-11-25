package com.servicethrottle.servicethrottlebackend.repositories;

import com.servicethrottle.servicethrottlebackend.models.UserAuthenticationCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthenticationCredentialsRepository extends JpaRepository<UserAuthenticationCredentials,Long> {

    Optional<UserAuthenticationCredentials> findOneByUsername(String username);

}

