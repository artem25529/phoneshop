package com.es.core.model.phone;

import java.util.Objects;

public class Stock {
    private Phone phone;
    private Integer stock;
    private Integer reserved;

    public Stock() {}

    public Stock(Phone phone, Integer stock, Integer reserved) {
        this.phone = phone;
        this.stock = stock;
        this.reserved = reserved;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock1 = (Stock) o;
        return Objects.equals(phone, stock1.phone);

    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
