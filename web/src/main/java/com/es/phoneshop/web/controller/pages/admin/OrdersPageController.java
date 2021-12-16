package com.es.phoneshop.web.controller.pages.admin;

import com.es.core.model.order.Order;
import com.es.core.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrdersPageController {

    @Resource
    private OrderServiceImpl orderService;

    @ModelAttribute
    public List<Order> insertOrders() {
        return orderService.getPlacedOrders();
    }

    @GetMapping
    public String admin() {
        return "admin";
    }

}
