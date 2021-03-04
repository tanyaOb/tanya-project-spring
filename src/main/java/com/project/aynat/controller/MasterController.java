package com.project.aynat.controller;

import com.project.aynat.domain.Order;
import com.project.aynat.domain.StateMaster;
import com.project.aynat.dto.PriceDTO;
import com.project.aynat.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MasterController {

    private final OrderService orderService;

    public MasterController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/master/checkorders")
    public String manageOrders(Authentication authentication, Map<String, Object> model) {
        User client = (User) authentication.getPrincipal();
        String masterName = client.getUsername();
        List<Order> orders = orderService.findAllMasterOrders(masterName);
        model.put("orders", orders);
        return "master/checkorders";
    }

    @GetMapping("/master/changeprogresstatus/{id}")
    public String loadProgressStatusPage(@PathVariable Long id, Map<String, Object> model) {
        model.put("id", id);
        return "master/changeprogresstatus";
    }

    @PostMapping("/master/changeprogresstatus")
    public String changeProgresStatus(Long id, StateMaster stateMaster, Map<String, Object> model) {
        boolean status = orderService.chooseProgressStatus(id, stateMaster);
        if (status) {
            model.put("message", "Progress status successfully changed!");
        } else {
            model.put("message", "Some problem occured!");
        }
        return "/master/changeprogresstatus";
    }

    @GetMapping("/master/assignprice/{id}")
    public String loadPricePage(@PathVariable Long id, Map<String, Object> model) {
        model.put("id", id);
        return "master/assignprice";
    }

    @PostMapping("/master/assignprice")
    public String loadPricePage(@Valid PriceDTO priceDTO, BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            for (Object object : bindingResult.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    model.put("message", fieldError.getDefaultMessage());
                }
            }
            return "/master/assignprice";
        }

        boolean status = orderService.setPrice(priceDTO);
        if (status) {
            model.put("message", "Price is assigned!");
        } else {
            model.put("message", "Price wasn't assigned! Some problem");
        }
        return "/master/assignprice";
    }
}
