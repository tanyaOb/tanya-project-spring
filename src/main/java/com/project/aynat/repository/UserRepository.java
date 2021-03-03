package com.project.aynat.repository;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AgencyUser, java.lang.Long> {

    AgencyUser findByUserName(String username);

    List<AgencyUser> findAllByUserRole(Role role);
}

