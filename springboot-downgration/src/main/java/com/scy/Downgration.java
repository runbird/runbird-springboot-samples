package com.scy;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名： Downgration <br>
 * 描述：TODO <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@SpringBootApplication
public class Downgration implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Downgration.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
