package com.es.core.service.impl;

import com.es.core.dao.ColorDao;
import com.es.core.dao.PhoneDao;
import com.es.core.model.phone.Phone;
import com.es.core.model.search.SearchStructure;
import com.es.core.service.PhoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private ColorDao colorDao;

    @Override
    public Optional<Phone> getPhone(Long id) {
        return phoneDao.get(id);
    }

    @Override
    public List<Phone> findPhones(SearchStructure searchStructure, int offset, int limit) {
        String query = searchStructure.getQuery();

        if (query != null && !query.isEmpty()) {
            searchStructure.setQuery(query.trim().toLowerCase(Locale.ROOT));
        }

        return phoneDao.findAll(searchStructure, offset, limit);
    }

    @Override
    public void save(Phone phone) {
        phoneDao.save(phone);
        colorDao.saveAll(phone.getColors());
        phoneDao.updatePhoneColors(phone.getId(), phone.getColors());
    }

    @Override
    public long count(String query) {
        if (query != null) {
            query = query.trim().toLowerCase();
        }

        return phoneDao.count(query);
    }
}
