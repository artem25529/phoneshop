package com.es.core.dao;

import com.es.core.model.phone.Stock;

import java.util.Optional;

public interface StockDao {
    Optional<Stock> get(Long phoneId);
    void save(Stock stock);
    void update(Stock stock);
}
