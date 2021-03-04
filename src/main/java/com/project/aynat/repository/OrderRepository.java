package com.project.aynat.repository;

import com.project.aynat.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByMasterId(String masterName);

    List<Order> findOrderByClientId_UserName(String clientName);

    @Modifying
    @Transactional
    @Query(value = "update user_orders uo set uo.state_master = :stateMaster where uo.id = :id", nativeQuery = true)
    void insertMasterState(@Param("stateMaster") String stateMaster, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update user_orders uo set uo.order_price = :orderPrice where uo.id = :id", nativeQuery = true)
    void insertPriceForOrder(@Param("orderPrice") int orderPrice, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update user_orders uo set uo.state_manager = :stateManager where uo.id = :id", nativeQuery = true)
    void insertManagerState(@Param("stateManager") String stateManager, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update user_orders uo set uo.master_id = :master where uo.id = :id", nativeQuery = true)
    void insertMasterForOrder(@Param("master") String master, @Param("id") Long id);

}
