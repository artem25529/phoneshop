package com.es.core.model.phone;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPhoneDao implements PhoneDao{
    private static final String FIND_PHONE_BY_ID = "select * from phones where id = ?";
    private static final String FIND_ALL_pHONES = "select * from phones offset ? limit ?";
    private static final String INSERT_PHONE = "insert into phones (id, brand, model, price, displaySizeInches, " +
            "weightGr, lengthMm, widthMm, heightMm, announced, deviceType, os, displayResolution, pixelDensity, " +
            "displayTechnology, backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb, " +
            "batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, description) " +
            "values (:id, :brand, :model, :price, :displaySizeInches, :weightGr, :lengthMm, :widthMm, :heightMm, " +
            ":announced, :deviceType, :os, :displayResolution, :pixelDensity, :displayTechnology, " +
            ":backCameraMegapixels, :frontCameraMegapixels, :ramGb, :internalStorageGb, :batteryCapacityMah, " +
            ":talkTimeHours, :standByTimeHours, :bluetooth, :positioning, :imageUrl, :description)";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    private PhoneMapper phoneMapper;

    @Override
    public Optional<Phone> get(final Long key) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_PHONE_BY_ID, phoneMapper, key));
    }

    public void save(final Phone phone) {
        namedParameterJdbcTemplate.update(INSERT_PHONE, new BeanPropertySqlParameterSource(phone));
    }

    public List<Phone> findAll(int offset, int limit) {
        if (offset < 0 || limit < 0) {
            throw new IllegalArgumentException("Offset and length params can't be lower than zero");
        }
        return jdbcTemplate.query(FIND_ALL_pHONES, new BeanPropertyRowMapper(Phone.class), offset, limit);
    }
}
