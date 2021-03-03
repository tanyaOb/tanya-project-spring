package com.project.aynat.repository;

import com.project.aynat.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByMasterId(String mastername);

    List<Order> findOrderByClientId_UserName(String clientname);

    List<Order> findAllById(Long id);
}
