package com.es.phoneshop.web.controller.dto;

public class QuickOrderItem {
    private String model;
    private String quantity;

    public QuickOrderItem() {

    }

    public QuickOrderItem(String model, String quantity) {
        this.model = model;
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "QuickOrderItem{" +
                "model='" + model + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
