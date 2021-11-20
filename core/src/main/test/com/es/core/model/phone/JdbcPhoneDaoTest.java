package com.es.core.model.phone;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/testApplicationContext-core.xml")
public class JdbcPhoneDaoTest {

    @Resource
    private JdbcPhoneDao jdbcPhoneDao;

    private static final Phone phone = new Phone();

    @BeforeClass
    public static void beforeClass() throws Exception {
        phone.setId(1L);
        phone.setModel("someModel");
        phone.setBrand("someBrand");
    }

    @Test
    public void testSaveAndGet() {
        assertTrue(jdbcPhoneDao.get(1000L).isPresent());
        jdbcPhoneDao.save(phone);
        Optional<Phone> optionalPhone = jdbcPhoneDao.get(1L);
        assertTrue(optionalPhone.isPresent());
        assertEquals(optionalPhone.get().getId(), phone.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindAllWithIncorrectParams() {
        jdbcPhoneDao.findAll(0, -1);
    }

    @Test
    public void testFinAll() {
        List<Phone> phoneList = jdbcPhoneDao.findAll(0, 10);
        assertFalse(phoneList.isEmpty());
    }
}



