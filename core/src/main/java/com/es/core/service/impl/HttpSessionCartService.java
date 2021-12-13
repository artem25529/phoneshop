package com.es.core.service.impl;

import com.es.core.model.cart.Cart;
import com.es.core.model.cart.CartItem;
import com.es.core.dao.PhoneDao;
import com.es.core.dao.StockDao;
import com.es.core.exception.EntityNotFoundException;
import com.es.core.exception.OutOfStockException;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;
import com.es.core.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service("cartService")
public class HttpSessionCartService implements CartService {
    @Resource
    private Cart cart;
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private StockDao stockDao;

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        Phone phone = phoneDao.get(phoneId).orElseThrow(() -> new EntityNotFoundException("Phone", phoneId));

        Long inCartQuantity = findCartItem(phoneId)
                .map(CartItem::getQuantity)
                .orElse(0L);

        addItemToCart(phone, quantity, inCartQuantity);
    }

    @Override
    public void update(Map<Long, Long> items) {
        for (Map.Entry<Long, Long> entry : items.entrySet()) {
            Long phoneId = entry.getKey();
            Long quantity = entry.getValue();

            Phone phone = phoneDao.get(phoneId).orElseThrow(() -> new EntityNotFoundException("Phone", phoneId));
            addItemToCart(phone, quantity, 0L);
        }
    }

    @Override
    public void remove(Long phoneId) {
        cart.removeItem(phoneId);
        recalculateCart();
    }

    @Override
    public void clearCart() {
        cart.setItems(new LinkedHashMap<>());
        cart.setTotalQuantity(0L);
        cart.setTotalPrice(BigDecimal.ZERO);
    }

    private Optional<CartItem> findCartItem(Long phoneId) {
        return Optional.ofNullable(cart.getItems().get(phoneId));
    }

    private void addItemToCart(Phone phone, Long quantity, Long inCartQuantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        Optional<Stock> stockOptional = stockDao.get(phone.getId());
        long quantityInStock = (long) stockOptional.map(Stock::getStock).orElse(0);

        long requestQuantity = inCartQuantity + quantity;
        if (requestQuantity > quantityInStock) {
            throw new OutOfStockException(quantity, quantityInStock - inCartQuantity);
        }

        cart.addItem(phone.getId(), new CartItem(phone, requestQuantity));
        recalculateCart();

    }

    private void recalculateCart() {
        long totalQuantity = cart.getItems()
                .values()
                .stream()
                .mapToLong(CartItem::getQuantity)
                .sum();

        BigDecimal totalPrice = cart.getItems()
                .values()
                .stream()
                .map(item -> item.getPhone().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalQuantity(totalQuantity);
        cart.setTotalPrice(totalPrice);
    }
}
