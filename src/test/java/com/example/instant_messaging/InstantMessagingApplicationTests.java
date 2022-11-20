package com.example.instant_messaging;

import com.alibaba.fastjson.JSON;
import com.example.instant_messaging.bean.Message;
import com.example.instant_messaging.dao.daoUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class InstantMessagingApplicationTests {
    @Autowired
    daoUser dao;
    @Test
    void contextLoads() {
        System.out.println(dao.selectByName("www1"));
    }

}
