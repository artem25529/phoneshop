package com.es.core.model.cart;

import com.es.core.model.phone.Phone;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {

    private Phone phone;
    private long quantity;

    public CartItem() {

    }

    public CartItem(Phone phone, long quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(phone, cartItem.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}