package com.project.aynat.service;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.domain.Order;
import com.project.aynat.domain.StateManager;
import com.project.aynat.domain.StateMaster;
import com.project.aynat.dto.OrderDTO;
import com.project.aynat.dto.PriceDTO;
import com.project.aynat.repository.OrderRepository;
import com.project.aynat.repository.UserRepository;
import com.project.aynat.util.DTOtoOrder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private static final Logger LOG = Logger.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOtoOrder dtoToOrderConverter;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public boolean chooseMaster(Long id, String master) {
        try {
            orderRepository.insertMasterForOrder(master, id);
        } catch (Exception e) {
            LOG.error("Problem occurred during choose master method", e);
            return false;
        }
        return true;
    }

    @Transactional
    public boolean choosePaymentStatus(Long orderId, StateManager stateManager) {
        if (stateManager == StateManager.PAID) {
            countMoney(orderId);
        }
        try {
            orderRepository.insertManagerState(stateManager.name(), orderId);
        } catch (Exception e) {
            LOG.error("Problem occurred during choose payment status method", e);
            return false;
        }
        return true;
    }

    public List<Order> findAllMasterOrders(String masterName) {
        return orderRepository.findAllByMasterId(masterName);
    }

    public boolean chooseProgressStatus(Long id, StateMaster stateMaster) {
        try {
            orderRepository.insertMasterState(stateMaster.name(), id);
        } catch (Exception e) {
            LOG.error("Problem occurred during choose progress status method", e);
            return false;
        }
        return true;
    }

    public boolean setPrice(PriceDTO priceDTO) {
        try {
            orderRepository.insertPriceForOrder(priceDTO.getOrderPrice(), priceDTO.getId());
        } catch (Exception e) {
            LOG.error("Problem occurred during setting price method", e);
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
            LOG.error("Problem occurred during inserting into user_orders table", e);
            return false;
        }
        return true;
    }

    @Transactional
    public boolean countMoney(Long orderId) {
        try {
            Optional<Order> order = orderRepository.findById(orderId);
            AgencyUser client = order.get().getClientId();
            client.setAccount(client.getAccount() - order.get().getOrderPrice());
            userRepository.save(client);
        } catch (Exception e){
            LOG.error("Problem occurred during count money method", e);
            return false;
        }
        return true;
    }
}
