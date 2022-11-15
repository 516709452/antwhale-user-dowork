package org.antwhale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: 何欢
 * @Date: 2022/7/2110:42
 * @Description:
 */
@EnableScheduling
@MapperScan("org.antwhale.mapper")
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"org.antwhale","com.antwhale"})
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
