package com.es.core.service.impl;

import com.es.core.model.order.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacedOrdersService {
    private List<Order> orderList = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }

    public Order findOrder(String id) {
        return orderList.stream()
                .filter(order -> order.getId().equalsIgnoreCase(id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Order with id: " + id + " not found"));
    }
}
