package com.es.phoneshop.web.controller.pages;

import com.es.core.model.order.Order;
import com.es.core.service.impl.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {

    @Resource
    private OrderServiceImpl orderService;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleOrderNotFoundEx(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping("{id}")
    public String orderOverView(@PathVariable String id, Model model) {
        Order order = orderService.getPlacedOrder(id);
        model.addAttribute("order", order);
        return "orderOverview";
    }
}
