package com.project.aynat.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "model")
    private String modelOrder;
    @Column(name = "description")
    private String description;
    @Column(name = "state_master")
    private String stateMaster;
    @Column(name = "state_manager")
    private String stateManager;
    @Column(name = "master_id")
    private String masterId;
    @Column(name = "order_price")
    private Integer orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private AgencyUser clientId;

    public AgencyUser getClientId() {
        return clientId;
    }

    public void setClientId(AgencyUser clientId) {
        this.clientId = clientId;
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

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
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
