package com.vere.assign_online.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * ClassName:SpringConfiguration
 * Package:com.vere.demo.config
 * Description:
 *
 * @Date:2022/4/14 9:40
 * @Author:3046990030@qq.com
 */

@Configuration
@ComponentScan("com.vere.assign_online")
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {
}
