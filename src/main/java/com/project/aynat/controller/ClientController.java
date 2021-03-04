package com.project.aynat.controller;

import com.project.aynat.domain.Order;
import com.project.aynat.dto.OrderDTO;
import com.project.aynat.service.OrderService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {

    private final OrderService orderService;

    public ClientController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/client/myorder")
    public String getOrders(Authentication authentication, Map<String, Object> model) {
        User client = (User) authentication.getPrincipal();
        List<Order> orders = orderService.getOrdersByUserName(client.getUsername());
        model.put("orders", orders);
        return "client/myorder";
    }

    @GetMapping("/client/neworder")
    public String newOrder() {
        return "client/neworder";
    }

    @PostMapping("/client/neworder")
    public String createOrder(@Valid OrderDTO orderDTO, BindingResult bindingResult, Authentication authentication, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            for (Object object : bindingResult.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    model.put("message", fieldError.getDefaultMessage());
                }
            }
            return "client/neworder";
        }

        User client = (User) authentication.getPrincipal();
        String username = client.getUsername();
        boolean status = orderService.insertIntoOrders(username, orderDTO);
        if (status) {
            model.put("message", "Order successfully added");
        } else {
            model.put("message", "Order wasn't added. Some problem occured");
        }
        return "client/neworder";
    }
}

