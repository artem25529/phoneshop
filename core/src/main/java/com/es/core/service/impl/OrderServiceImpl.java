package com.es.core.service.impl;

import com.es.core.dao.StockDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.cart.CartItem;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.phone.Phone;
import com.es.core.service.CartService;
import com.es.core.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private StockDao stockDao;

    @Resource
    private Cart cart;

    @Resource
    private CartService cartService;

    private List<Order> placedOrders = new ArrayList<>();

    @Override
    public Order createOrder() {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItem> cartItems = new ArrayList<>(cart.getItems().values());

        cartItems.forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setPhone(cartItem.getPhone());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        });

        order.setOrderItems(orderItems);
        BigDecimal subtotal =  cartItems.stream()
                .map(CartItem::getPhone)
                .map(Phone::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        order.setSubtotal(subtotal);
        order.setDeliveryPrice(new BigDecimal(5));
        order.setTotalPrice(subtotal.add(new BigDecimal(5)));
        return order;

    }

    @Override
    public void placeOrder(Order order) {
        order.setSecureId(UUID.randomUUID().toString());
        List<OrderItem> orderItems = order.getOrderItems();

        orderItems.forEach(orderItem -> {
            Phone phone = orderItem.getPhone();
            Long quantity = orderItem.getQuantity();
            stockDao.get(phone.getId()).ifPresent(stock -> {
                stock.setStock(stock.getStock() - quantity.intValue());
                stock.setStock(stock.getReserved() - quantity.intValue());
                stockDao.update(stock);
            });

        });
        cartService.clearCart();
        placedOrders.add(order);
    }

    public Order getPlacedOrder(String id) {
        return placedOrders.stream()
                .filter(order -> order.getSecureId().equalsIgnoreCase(id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));
    }
}
