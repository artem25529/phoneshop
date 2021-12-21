package com.es.phoneshop.web.controller.dto;

import java.util.List;

public class QuickOrderItemDto {
    private List<QuickOrderItem> quickOrderItemList;

    public QuickOrderItemDto() {

    }

    public QuickOrderItemDto(List<QuickOrderItem> quickOrderItemList) {
        this.quickOrderItemList = quickOrderItemList;
    }

    public List<QuickOrderItem> getQuickOrderItemList() {
        return quickOrderItemList;
    }

    public void setQuickOrderItemList(List<QuickOrderItem> quickOrderItemList) {
        this.quickOrderItemList = quickOrderItemList;
    }
}
