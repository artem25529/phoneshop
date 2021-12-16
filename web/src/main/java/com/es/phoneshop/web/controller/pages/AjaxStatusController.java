package com.es.phoneshop.web.controller.pages;

import com.es.core.service.OrderService;
import com.es.phoneshop.web.controller.dto.StatusDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AjaxStatusController {

    @Resource
    private OrderService orderService;

    @PostMapping("/setStatus")
    public void setStatus(@RequestBody StatusDto statusDto) {
        orderService.setStatus(statusDto.getId(), statusDto.getOrderStatus());
    }
}
