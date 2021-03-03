package com.project.aynat.util;

import com.project.aynat.domain.Order;
import com.project.aynat.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DTOtoOrder implements Converter<OrderDTO, Order> {

    @Override
    public Order convert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCategory(orderDTO.getCategory());
        order.setModelOrder(orderDTO.getModelOrder());
        order.setDescription(orderDTO.getDescription());
        order.setMasterId(order.getMasterId());
        order.setOrderPrice(order.getOrderPrice());
        order.setStateMaster(order.getStateMaster());
        order.setStateManager(order.getStateManager());
        order.setClientId(orderDTO.getClientId());
        return order;
    }
}
