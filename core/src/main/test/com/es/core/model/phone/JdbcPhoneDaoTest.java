package com.es.core.model.phone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/context/applicationContext-core.xml")
public class JdbcPhoneDaoTest {

    @Autowired
    private JdbcPhoneDao jdbcPhoneDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static DriverManagerDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        jdbcTemplate.setDataSource(dataSource);
        jdbcPhoneDao.setJdbcTemplate(jdbcTemplate);

        File file = new File("src\\main\\test\\testschema.sql");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        jdbcTemplate.execute(sb.toString());
    }

    @Test
    public void testPhoneDao() {
        List<Phone> all = jdbcPhoneDao.findAll(0, 10);
        assertTrue(all.size() > 0);

        Phone newPhone = getPhoneObject();
        jdbcPhoneDao.save(newPhone);
        Phone phone1 = jdbcPhoneDao.get(1003L).get();
        assertNotNull(phone1);
        assertEquals(phone1.getBrand(), newPhone.getBrand());
    }

    private Phone getPhoneObject() {
        Phone phone = new Phone();
        phone.setBrand("brand");
        phone.setModel("model");
        phone.setPrice(new BigDecimal(1000));
        phone.setDisplaySizeInches(new BigDecimal(1));
        phone.setWeightGr(100);
        phone.setLengthMm(new BigDecimal(1));
        phone.setWidthMm(new BigDecimal(1));
        phone.setHeightMm(new BigDecimal(1));
        phone.setAnnounced(new Date(12312334));
        phone.setDeviceType("type");
        phone.setOs("os");
        phone.setDisplayResolution("res");
        phone.setPixelDensity(123);
        phone.setDisplayTechnology("tech");
        phone.setBackCameraMegapixels(new BigDecimal(1));
        phone.setFrontCameraMegapixels(new BigDecimal(1));
        phone.setRamGb(new BigDecimal(1));
        phone.setInternalStorageGb(new BigDecimal(1));
        phone.setBackCameraMegapixels(new BigDecimal(1));
        phone.setTalkTimeHours(new BigDecimal(1));
        phone.setStandByTimeHours(new BigDecimal(1));
        phone.setBluetooth("bl");
        phone.setPositioning("pos");
        phone.setImageUrl("url");
        phone.setDescription("description");
        return phone;
    }
}



