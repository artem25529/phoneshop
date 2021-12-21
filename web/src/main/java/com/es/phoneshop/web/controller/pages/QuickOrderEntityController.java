package com.es.phoneshop.web.controller.pages;

import com.es.core.model.phone.Phone;
import com.es.core.service.CartService;
import com.es.core.service.PhoneService;
import com.es.phoneshop.web.controller.dto.QuickOrderItem;
import com.es.phoneshop.web.controller.dto.QuickOrderItemDto;
import com.es.phoneshop.web.controller.validation.QuickOrderItemDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/quickOrderEntity")
public class QuickOrderEntityController {
    @Resource
    private PhoneService phoneServiceImpl;

    @Resource
    private CartService cartService;

    @Resource
    private QuickOrderItemDtoValidator quickOrderItemDtoValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(quickOrderItemDtoValidator);
    }
    @GetMapping
    public String showEntityForm() {
        return "quickOrderEntity";
    }

    @ModelAttribute
    public QuickOrderItemDto quickOrderItemDto() {
        return new QuickOrderItemDto();
    }

    @PostMapping
    public String addToCart(@ModelAttribute @Valid QuickOrderItemDto quickOrderItemDto, BindingResult bindingResult) {
        List<QuickOrderItem> quickOrderItemList = quickOrderItemDto.getQuickOrderItemList();

        for (int i = 0; i < quickOrderItemList.size(); i++) {
            if (bindingResult.getFieldError("quickOrderItemList[" + i + "].model") == null &&
            bindingResult.getFieldError("quickOrderItemList[" + i + "].quantity") == null) {
                QuickOrderItem quickOrderItem = quickOrderItemList.get(i);
                Optional<Phone> optionalPhone = phoneServiceImpl.findByModel(quickOrderItem.getModel());
                Phone phone = optionalPhone.get();
                long quantity = Long.parseLong(quickOrderItem.getQuantity());
                cartService.addPhone(phone.getId(), quantity);
            }
        }
        if (bindingResult.hasErrors()) {
            return "quickOrderEntity";
        }


        return "redirect:/quickOrderEntity";
    }
}
