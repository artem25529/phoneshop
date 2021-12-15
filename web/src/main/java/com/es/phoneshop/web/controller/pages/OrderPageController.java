package com.es.phoneshop.web.controller.pages;

import com.es.core.model.order.Order;
import com.es.core.service.impl.OrderServiceImpl;
import com.es.phoneshop.web.controller.validation.OrderDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {

    @Resource
    private OrderDtoValidator orderDtoValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(orderDtoValidator);
    }

    @Resource
    private OrderServiceImpl orderService;

    @ModelAttribute("order")
    public Order insertOrder() {
        return orderService.createOrder();
    }


    @GetMapping
    public String getOrder() {
        return "order";
    }

    @PostMapping
    public String placeOrder(@ModelAttribute @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "order";
        }
        orderService.placeOrder(order);
        return "redirect:/orderOverview/" + order.getSecureId();
    }
}
