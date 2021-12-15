package com.es.phoneshop.web.controller.pages;

import com.es.core.exception.EntityNotFoundException;
import com.es.core.model.phone.Phone;
import com.es.core.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    @Resource
    private PhoneService phoneService;

    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Phone phone = phoneService.getPhone(id).orElseThrow(() ->  new EntityNotFoundException("phone", id));
        model.addAttribute(phone);
        return "productDetails";
    }

}
