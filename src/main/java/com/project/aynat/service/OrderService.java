package com.project.aynat.service;

import com.project.aynat.domain.*;
import com.project.aynat.dto.OrderDTO;
import com.project.aynat.repository.OrderRepository;
import com.project.aynat.repository.UserRepository;
import com.project.aynat.util.DTOtoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOtoOrder dtoToOrderConverter;

    @Autowired

    public Iterable<Order> findAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return orders;
    }

    @Transactional
    public boolean chooseMaster(Long id, String masterForOrder) {
        Order order = orderRepository.findById(id).orElse(null);
        order.setMasterId(masterForOrder);
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean choosePaymentStatus(Long orderId, StateManager paymentStatus) {
        if (paymentStatus == StateManager.PAID) {
            countMoney(orderId);
        }
        Order order = orderRepository.findById(orderId).orElse(null);
        order.setStateManager(paymentStatus.name());
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Iterable<Order> findAllMasterOrders(String masterName) {
        Iterable<Order> orders = orderRepository.findAllByMasterId(masterName);
        return orders;
    }

    @Transactional
    public boolean changeProgress(Long Id, StateMaster progressStatus) {
        try {
            Order order = orderRepository.findById(Id).orElse(null);
            order.setStateMaster(progressStatus.name());
            orderRepository.save(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean setPrice(Long id, int assignedPrice) {
        try {
            Order order = orderRepository.findById(id).orElse(null);
            order.setOrderPrice(assignedPrice);
            orderRepository.save(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Order> getOrdersByUserName(String username) {
        return orderRepository.findOrderByClientId_UserName(username);
    }

    @Transactional
    public boolean insertIntoOrders(String username, OrderDTO orderDTO) {
        try {
            Order order = dtoToOrderConverter.convert(orderDTO);
            AgencyUser client = userRepository.findByUserName(username);
            order.setClientId(client);
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public boolean countMoney(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        AgencyUser client = order.get().getClientId();
        client.setAccount(client.getAccount() - order.get().getOrderPrice());
        userRepository.save(client);
        return true;
    }
}
