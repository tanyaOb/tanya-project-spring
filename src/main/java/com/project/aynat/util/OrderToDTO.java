package com.project.aynat.util;

import com.project.aynat.domain.Order;
import com.project.aynat.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToDTO implements Converter<Order, OrderDTO> {

    @Override
    public OrderDTO convert(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCategory(order.getCategory());
        orderDTO.setModelOrder(order.getModelOrder());
        orderDTO.setDescription(order.getDescription());
        orderDTO.setMasterId(order.getMasterId());
        orderDTO.setOrderPrice(order.getOrderPrice());
        orderDTO.setStateMaster(order.getStateMaster());
        orderDTO.setStateManager(order.getStateManager());
        orderDTO.setClientId(order.getClientId());
        return orderDTO;
    }
}
