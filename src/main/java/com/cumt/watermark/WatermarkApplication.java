package com.cumt.watermark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WatermarkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatermarkApplication.class, args);
    }

}
