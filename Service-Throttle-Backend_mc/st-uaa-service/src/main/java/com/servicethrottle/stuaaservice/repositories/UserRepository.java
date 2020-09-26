package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
