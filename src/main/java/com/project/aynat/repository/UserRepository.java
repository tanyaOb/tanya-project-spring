package com.project.aynat.repository;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<AgencyUser, java.lang.Long> {

    AgencyUser findByUserName(String username);

    List<AgencyUser> findAllByUserRole(Role role);

    @Modifying
    @Transactional
    @Query(value = "update agency_users au set au.client_account = au.client_account + :account where au.user_name = :userName", nativeQuery = true)
    void updateAccountBalance(@Param("userName") String userName, @Param("account") int account);
}

