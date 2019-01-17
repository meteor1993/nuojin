package com.springboot.nuojin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.springboot.nuojin.RSAUtils;
import sun.misc.BASE64Encoder;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FuhuiApplicationTests {
    @Test
    public void contextLoads(){

    }
}
