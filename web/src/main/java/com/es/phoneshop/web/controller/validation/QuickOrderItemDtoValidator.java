package com.es.phoneshop.web.controller.validation;

import com.es.core.dao.impl.JdbcPhoneDao;
import com.es.core.dao.impl.JdbcStockDao;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;
import com.es.phoneshop.web.controller.dto.QuickOrderItem;
import com.es.phoneshop.web.controller.dto.QuickOrderItemDto;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class QuickOrderItemDtoValidator implements Validator {
    @Resource
    private JdbcPhoneDao jdbcPhoneDao;

    @Resource
    private JdbcStockDao jdbcStockDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return QuickOrderItemDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        QuickOrderItemDto quickOrderItemDto = (QuickOrderItemDto) o;
        List<QuickOrderItem> quickOrderItems = quickOrderItemDto.getQuickOrderItemList();

        for (int i = 0; i < quickOrderItems.size(); i++) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quickOrderItemList[" + i + "].model", "model.empty", "Model is empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quickOrderItemList[" + i + "].quantity", "quantity.empty", "Quantity is empty");

            QuickOrderItem quickOrderItem = quickOrderItems.get(i);
            String model = quickOrderItem.getModel();
            String quantityStr = quickOrderItem.getQuantity();

            Optional<Phone> optionalPhone = Optional.empty();

            try {
                optionalPhone = findByModel(model);
            } catch (DataAccessException e) {
                errors.rejectValue("quickOrderItemList[" + i + "].model", "model.notFound", "Model not found");
            }

            if (optionalPhone.isPresent()) {
                Phone phone = optionalPhone.get();
                long quantity = 0;
                try {
                    quantity = Long.parseLong(quantityStr);
                } catch (NumberFormatException e) {
                    errors.rejectValue("quickOrderItemList[" + i + "].quantity", "quantity.invalidFormat", "Invalid quantity format");
                }

                Optional<Stock> optionalStock = getStock(phone.getId());

                if (optionalStock.isPresent()) {
                    if (quantity > optionalStock.get().getStock()) {
                        errors.rejectValue("quickOrderItemList[" + i + "].quantity", "quantity.outOfStock", "Quantity is greater than stock");
                    }
                } else {
                    errors.rejectValue("quickOrderItemList[" + i + "].quantity", "quantity.emptyStock", "Stock is empty");
                }
            }
        }
    }

    private Optional<Phone> findByModel(String model) {
        return jdbcPhoneDao.findByModel(model);
    }

    private Optional<Stock> getStock(Long id) {
        return jdbcStockDao.get(id);
    }
}
