package com.vere.assign_online.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * ClassName:DataSourceTest
 * Package:com.vere.demo.test
 * Description:
 *
 * @Date:2022/4/14 9:43
 * @Author:3046990030@qq.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTest {
    @Resource
    private DataSource dataSource;

    @Test
    public void contextLoads() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("数据源>>>>>>" + dataSource.getClass());
            System.out.println("连接>>>>>>" + connection);
            System.out.println("连接地址>>>>>>" + connection.getMetaData().getURL());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataSource instanceof DruidDataSource ? "DruidDataSource" : "Other DataSource");
    }
}
