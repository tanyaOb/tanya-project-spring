package com.project.aynat.dto;

import javax.validation.constraints.Min;

public class PriceDTO {
    private Long id;
    @Min(value = 0, message = "Price should be a positive number")
    private Integer orderPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }
}
