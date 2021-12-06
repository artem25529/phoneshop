package com.es.core.model.cart;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Component
@SessionScope
public class Cart implements Serializable {
    private Map<Long, CartItem> items = new LinkedHashMap<>();
    private Long totalQuantity = 0L;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public Cart() {

    }

    public Cart(Map<Long, CartItem> items) {
        this.items.putAll(items);
    }

    public Map<Long, CartItem> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void setItems(Map<Long, CartItem> items) {
        this.items = items;
    }

    public void addItem(Long id, CartItem item) {
        items.put(id, item);
    }

    public void removeItem(Long id) {
        items.entrySet().removeIf(entry -> entry.getValue().getPhone().getId().equals(id));
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
