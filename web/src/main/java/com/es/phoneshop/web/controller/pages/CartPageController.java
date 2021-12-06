package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cart.Cart;
import com.es.core.model.cart.CartItem;
import com.es.core.service.CartService;
import com.es.phoneshop.web.controller.dto.CartItemDto;
import com.es.phoneshop.web.controller.dto.CartItemListDto;
import com.es.phoneshop.web.controller.validation.CartItemListDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartService cartService;

    @Resource
    private CartItemListDtoValidator cartItemListDtoValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(cartItemListDtoValidator);
    }

    @GetMapping
    public String getCart(Model model) {
        Map<Long, CartItem> items = cartService.getCart().getItems();
        model.addAttribute("items", items);
        return "cart";
    }

    @ModelAttribute("items")
    public Map<Long, CartItem> insertItems() {
        return cartService.getCart().getItems();
    }

    @ModelAttribute
    public CartItemListDto addCartItem() {
        List<CartItem> items = new ArrayList<>(cartService.getCart().getItems().values());

        List<CartItemDto> cartItemDtoList = items.stream()
                .map(item -> new CartItemDto(item.getPhone().getId(), item.getQuantity()))
                .collect(Collectors.toList());

        return new CartItemListDto(cartItemDtoList);
    }

    @PutMapping
    public String updateCart(@ModelAttribute @Valid CartItemListDto cartItems, BindingResult result) {
        if (result.hasErrors()) {
            return "cart";
        }
        Map<Long, Long> newCartItems = cartItems.getCartItems().stream()
                .collect(Collectors.toMap(CartItemDto::getId, CartItemDto::getQuantity));
        cartService.update(newCartItems);

        return "redirect:cart";
    }
}
