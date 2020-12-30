package com.half.boot05webadmin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
class Boot05WebAdminApplicationTests {

    @Test
    void contextLoads() {
        Long count = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        System.out.println(count);
        System.out.println(dataSource.getClass());

    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

}
