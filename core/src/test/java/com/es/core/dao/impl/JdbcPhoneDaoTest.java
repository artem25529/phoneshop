package com.es.core.dao.impl;

import com.es.core.model.phone.Phone;
import com.es.core.service.impl.PhoneServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context/testApplicationContext-core.xml")
public class JdbcPhoneDaoTest {

    @Resource
    private JdbcPhoneDao jdbcPhoneDao;

    @Before
    public void setUp() throws Exception {
        Phone phone = new Phone();
        phone.setId(200L);
        phone.setBrand("Huawei");
        phone.setModel("Huawei p40");
        jdbcPhoneDao.save(phone);
    }

    @Test
    public void saveTest() {
        Optional<Phone> optionalPhone = jdbcPhoneDao.get(200L);
        assertTrue(optionalPhone.isPresent());
        assertEquals(optionalPhone.get().getModel(), "Huawei p40");
    }


}