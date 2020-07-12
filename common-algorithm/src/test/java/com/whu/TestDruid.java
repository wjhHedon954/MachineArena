package com.whu;

import com.mapper.AlgorithmTypeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * @author Hedon Wang
 * @create 2020-07-11 15:40
 */

@SpringBootTest
public class TestDruid {

    @Autowired
    DataSource dataSource;

    @Autowired
    AlgorithmTypeMapper algorithmTypeMapper;

    @Test
    public void test() throws Exception{
        //class com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper
        System.out.println(dataSource.getClass());
        //com.mysql.cj.jdbc.ConnectionImpl@d9f5fce
        System.out.println(dataSource.getConnection());
    }


}
