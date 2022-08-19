package org.antwhale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 何欢
 * @Date: 2022/7/2110:42
 * @Description:
 */
@MapperScan("org.antwhale.mapper")
@SpringBootApplication(scanBasePackages = {"org.antwhale"})
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
