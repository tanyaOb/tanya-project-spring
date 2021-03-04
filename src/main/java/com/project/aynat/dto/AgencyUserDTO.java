package com.project.aynat.dto;

import com.project.aynat.domain.Order;
import com.project.aynat.domain.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class AgencyUserDTO {
    private Long id;
    private Role userRole;
    @NotNull(message = "User name is mandatory")
    @Email(message = "Email should be valid")
    @Size(min = 2, max = 40, message = "User name should be between 2 and 40 characters")
    private String userName;
    @NotNull(message = "Password is mandatory")
    @Size(min = 5, max = 20, message = "Password size should be between 2 and 20 characters")
    private String password;
    private Integer account;
    private boolean active;
    private List<Order> orders = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
