package com.project.aynat.dto;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.domain.Category;

import javax.validation.constraints.NotEmpty;

public class OrderDTO {
    private Long id;
    private Category category;
    @NotEmpty(message = "Model field can't be empty")
    private String modelOrder;
    private String description;
    private String stateMaster;
    private String stateManager;
    private String masterId;
    private Integer orderPrice;
    private AgencyUser clientId;

    public AgencyUser getClientId() {
        return clientId;
    }

    public void setClientId(AgencyUser clientId) {
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getModelOrder() {
        return modelOrder;
    }

    public void setModelOrder(String modelOrder) {
        this.modelOrder = modelOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStateMaster() {
        return stateMaster;
    }

    public void setStateMaster(String stateMaster) {
        this.stateMaster = stateMaster;
    }

    public String getStateManager() {
        return stateManager;
    }

    public void setStateManager(String stateManager) {
        this.stateManager = stateManager;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }
}
