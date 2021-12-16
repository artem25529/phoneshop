package com.es.phoneshop.web.controller.dto;

import com.es.core.model.order.OrderStatus;

public class StatusDto {
    private Long id;
    private OrderStatus orderStatus;

    public StatusDto() {

    }

    public StatusDto(Long id, OrderStatus orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
