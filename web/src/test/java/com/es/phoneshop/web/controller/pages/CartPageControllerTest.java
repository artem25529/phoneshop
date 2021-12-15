package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cart.Cart;
import com.es.core.service.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class CartPageControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @Mock
    private Cart cart;

    @InjectMocks
    private CartPageController cartPageController;

    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(cartPageController)
                .setSingleView(new InternalResourceView("/WEB-INF/pages/cart"))
                .build();
    }

    @Test
    public void getCart() throws Exception {
        when(cartService.getCart()).thenReturn(cart);
        mockMvc.perform(get("/cart"))
                .andExpect(view().name("cart"));
    }

    @Test
    public void insertItems() {
    }

    @Test
    public void addCartItem() {
    }

    @Test
    public void updateCart() {
    }
}