package com.example.demo;

import com.example.demo.bean.ProjectMsg;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@PropertySource("classpath:config/application.properties")
@RunWith(SpringRunner.class)
class YupingguApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void test01(){

    }

}
