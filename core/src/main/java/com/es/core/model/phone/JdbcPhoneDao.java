package com.es.core.model.phone;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPhoneDao implements PhoneDao{
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Phone> get(final Long key) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from phones where id=?",
                new BeanPropertyRowMapper<>(Phone.class), key));
    }

    public void save(final Phone phone) {
        jdbcTemplate.update("insert into phones (brand, model, price, displaySizeInches, weightGr, " +
                        "lengthMm, widthMm, heightMm, announced, deviceType, os, displayResolution," +
                        "pixelDensity, displayTechnology, backCameraMegapixels, frontCameraMegapixels," +
                        "ramGb, internalStorageGb, batteryCapacityMah, talkTimeHours, standByTimeHours," +
                        "bluetooth, positioning, imageUrl, description) values " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(),
                phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(),
                phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(),
                phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(),
                phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(),
                phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(),
                phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription());
    }

    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query("select * from phones offset " + offset + " limit " + limit,
                new BeanPropertyRowMapper(Phone.class));
    }
}
