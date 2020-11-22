package com.servicethrottle.stuaaservice.repositories;

import com.servicethrottle.stuaaservice.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
}
