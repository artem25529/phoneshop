package com.es.core.model.phone;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PhoneMapper extends BeanPropertyRowMapper<Phone> {
    private final static String GET_PHONE_COLOR = "select * from colors inner join phone2color as p2c " +
            "on colors.id = p2c.colorId where p2c.phoneId = :phoneId";

    public PhoneMapper(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(Phone.class);
    }

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Phone mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Phone phone = super.mapRow(rs, rowNumber);
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("phoneId", phone.getId());
        Set<Color> colorSet = new HashSet<>(namedParameterJdbcTemplate.query(GET_PHONE_COLOR, source, new BeanPropertyRowMapper<>(Color.class)));
        phone.setColors(colorSet);
        return phone;
    }
}
