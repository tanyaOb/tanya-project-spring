package com.project.aynat.controller;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.domain.Order;
import com.project.aynat.domain.Role;
import com.project.aynat.domain.StateManager;
import com.project.aynat.dto.AccountDTO;
import com.project.aynat.service.OrderService;
import com.project.aynat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {

    private final OrderService orderService;

    private final UserService userService;


    public ManagerController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/manager/manageorders")
    public String manageOrders(Map<String, Object> model) {
        List<Order> orders = orderService.findAllOrders();
        model.put("orders", orders);
        return "manager/manageorders";
    }

    @GetMapping("/manager/changepaymentstatus/{id}")
    public String loadPaymentStatusPage(@PathVariable Long id, Map<String, Object> model) {
        model.put("order_id", id);
        return "manager/changepaymentstatus";
    }

    @GetMapping("/manager/manageuseracounts")
    public String loadClientAccountPage(Map<String, Object> model) {
        List<AgencyUser> clients = userService.finAllByUserRole(Role.CLIENT);
        model.put("clients", clients);
        return "manager/manageuseracounts";
    }

    @GetMapping("/manager/addmoney/{name}")
    public String addMoneyToAccount(@PathVariable String name, Map<String, Object> model) {
        model.put("client_name", name);
        return "manager/addmoney";
    }

    @PostMapping("/manager/addmoney")
    public String addMoneyUserAccount(@Valid AccountDTO accountDTO, BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            for (Object object : bindingResult.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    model.put("message", fieldError.getDefaultMessage());
                    return "/manager/addmoney";
                }
            }
        }

        boolean status = userService.loadMoneyOnUserAccount(accountDTO);
        if (status) {
            model.put("message", "Money successfully added!");
        } else {
            model.put("message", "Some problem occured!");
        }
        return "/manager/addmoney";
    }

    @GetMapping("/manager/choosemaster/{id}")
    public String loadMastersPage(@PathVariable Long id, Map<String, Object> model) {
        List<AgencyUser> masterList = userService.findAllMasters(Role.MASTER);
        model.put("order_id", id);
        model.put("masters", masterList);
        return "manager/choosemaster";
    }

    @PostMapping("/manager/choosemaster")
    public void chooseMasterForOrder(@RequestParam String masterForOrder, Long orderId, Map<String, Object> model) {
        boolean status = orderService.chooseMaster(orderId, masterForOrder);
        if (status) {
            model.put("message", "Master successfully added!");
        } else {
            model.put("message", "Some problem occured!");
        }
    }

    @PostMapping("/manager/changepaymentstatus")
    public void changePaymentStatus(StateManager paymentStatus, Long orderId, Map<String, Object> model) {
        boolean status = orderService.choosePaymentStatus(orderId, paymentStatus);
        if (status) {
            model.put("message", "Payment status successfully changed!");
        } else {
            model.put("message", "Some problem occured!");
        }
    }
}
