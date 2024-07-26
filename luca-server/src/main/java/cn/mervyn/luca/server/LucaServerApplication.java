package cn.mervyn.luca.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${luca.info.base-package}
@SpringBootApplication(scanBasePackages = {"${luca.info.base-package}.server", "${luca.info.base-package}.module"})
public class LucaServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(LucaServerApplication.class, args);

    }

}
