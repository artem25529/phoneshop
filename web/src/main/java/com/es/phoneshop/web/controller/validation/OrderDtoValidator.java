package com.es.phoneshop.web.controller.validation;

import com.es.core.dao.StockDao;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.phone.Stock;
import com.es.phoneshop.web.controller.dto.OrderDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDtoValidator implements Validator {

    @Resource
    private StockDao stockDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;
        List<OrderItem> orderItems = order.getOrderItems();

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            Long quantity = orderItem.getQuantity();

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderItems[" + i + "].quantity", "order.orderItems[" + i + "].quantity", "Empty quantity");

            if (quantity != null && quantity < 0) {
                errors.rejectValue("orderItems[" + i + "].quantity", "order.orderItems[" + i + "].quantity", "Less than one");
            }

            Optional<Stock> stock = stockDao.get(orderItem.getPhone().getId());

            if (quantity > stock.map(Stock::getStock).orElse(0)) {
                errors.rejectValue("orderItems[" + i + "].quantity", "orderItems[" + i + "].quantity", "Out of stock");
            }
        }

        if (!order.getFirstName().matches("\\w{2,10}")) {
            errors.rejectValue("firstName", "order.firstName", "Invalid Name");
        }

        if (!order.getLastName().matches("\\w{2,10}")) {
            errors.rejectValue("lastName", "order.lastName", "Invalid last name");
        }

        if (!order.getDeliveryAddress().matches("\\w{2,10}")) {
            errors.rejectValue("deliveryAddress", "order.deliveryAddress","Invalid address");
        }

        if (!order.getContactPhoneNo().matches("(\\+375|80)\\(\\d{2}\\)\\d{7}")) {
            errors.rejectValue("contactPhoneNo", "order.contactPhoneNo","Invalid phone number");
        }
    }
}
