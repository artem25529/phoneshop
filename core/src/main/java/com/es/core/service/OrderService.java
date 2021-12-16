package com.es.core.service;

import com.es.core.model.cart.Cart;
import com.es.core.exception.OutOfStockException;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;

import java.util.List;

public interface OrderService {
    Order createOrder();
    void placeOrder(Order order) throws OutOfStockException;
    Order getPlacedOrder(String id);
    void setStatus(Long id, OrderStatus orderStatus);
}
