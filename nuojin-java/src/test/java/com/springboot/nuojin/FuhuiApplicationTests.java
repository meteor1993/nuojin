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

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
        //headers.add("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlaWQiOiJtZW1iZXIiLCJncm91cGlkIjoibWVtYmVyIiwidXNlcnR5cGUiOiJtZW1iZXIiLCJ1c2VyaWQiOiI1NTgxNTQiLCJ1c2VybmFtZSI6IjEzODE3OTM1MDUzIiwicGFya2xvdCI6IiJ9.J4D8Jl_nDP-_JAE0j8r7Q0o7eMbQkwJi_si3qIOg1t1IZj4GoEZOpsdDbwGJOcWDt0DKijGprYehmz-sHWEKGQ");

        String url = "http://116.62.190.147:8888/thirdsign/getAccessToken?appId=jpxyzq0f&appSecret=5fbecdfedb2f45edbed098e2abb31bbe";

        String url1 = "http://116.62.190.147:8888/thirdsign/getOrderByLpn";

        JSONObject postData = new JSONObject();
        postData.put("appId", "jpxyzq0f");
            postData.put("appSecret", "5fbecdfedb2f45edbed098e2abb31bbe");
        HttpEntity<String> formEntity = new HttpEntity<String>(postData.toString(), headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        JSONObject accessToken = JSON.parseObject(result);
        String accessTokenString = accessToken.getString("bizContent");
        JSONObject postData1 = new JSONObject();
        postData1.put("hour", 0);
        postData1.put("plateId", "苏AAAAAK");





        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfgOHNaMI+FKIvn2G7T5p1amxYIhIiRygJeojB1zlYw5riLshnqxWvcpFBgRQkMEaHSvAuyENhmziTeHjBpg8AB11lM3gqLXQAohOocrhfhopEbtcBuBRDMfbEPEVaU3JcOtyIFjgZIOiORrIMnlNDfcRhvlZvNOKqO+7hmGwrzQIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKLoU7+htrDGSxxThmjFPYyXI+PU3hAzjAOSVd70wVvRuFrn8HiwQiHmE+ZUSTmEYMNNK1oyld5rtj5lIy7vAf9mUJneEi7XuSQ9p5h5TYRaxmBnyiKbRNYF19vQm6tH13BSbx5qectEOFi5svl65GK6YZsOG/yMl84n/cdVoHj1AgMBAAECgYEAn9cS7TDPnw1A/08Yz03QQVS+mznulLjvBUYcyUEfQ2c32sI+mTxf9wDXv6QtoKi5hIcAJWUC1ZSb4Qa9CbzrGEsdiAyfYaZgQqx6Mw9urw3X5Vjs1oSm46j8yvvyiNK5FhZJoiQT7/rocbo1Sg5yXGMJzUFE9Vs6f0JI1okjBHUCQQDThac+a4rA4bNaNGj3Etj4+2viVxIKTRKKngXx5hqGX/EamH+YjMnBH+W9hdI0pOeCMImB5pJ0zDoIPQqJ0IVHAkEAxSm584pBGEl+KKB/NXczcJMzzWp/3o8Q5z6lD+PsQvAso7uQEi1jjG8AN8IbbnjMcwdC5M7u4CSuzHJnsfTd4wJAPTg5p39IsXqhhkIU/y2dTrK2DtRUjFayPxxtbA3FL48lISVyCJz258Zrn+bTaBkySCP0KHyrdtKFGU1Rd4G6kQJATWXO8JLicOVQ2CQBK/zqPZQbrtTS84xYB18pLu67bZ+Y9oGH0grtXFCq1jilyh0zrSg5k1bEoUrlL67tlAdbHwJAPqTebAWNjCuM5jV+pV/gdkXReDVjdPkjkvHxA2pyEsjJREmROEjrpxOkGZI4OBlRmr7Zf/zXyM5i70tflAWxVg==";



        //byte[] bytes = RSAUtils.encryptByPublicKey( Base64.decodeBase64(postData1.toJSONString()),publicKey); //RSAUtil.publicEncrypt(RSAUtil.base642Byte(postData1.toJSONString()), RSAUtil.string2PublicKey(publicKey));
        String jsonselect = "{\"hour\":0,\"plateId\":\"苏AAAAAK\"}";
        result = RSAUtils.encode(jsonselect,publicKey);
        JSONObject postData2 = new JSONObject();
        postData2.put("bizContent", result);

        //MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        //headers.setContentType(type);
        headers.add("accessToken", accessTokenString);
        headers.set("Accept-Charset", "utf-8");
        headers.set("Content-type", "application/json;charset=UTF-8");
        String bizString = postData2.toString();
        HttpEntity<String> formEntity2 = new HttpEntity<String>(bizString, headers);

        url1 += "?bizContent="+result;

        result = restTemplate.postForObject(url1, formEntity2, String.class);

        System.out.println(result);


//        bytes = RSAUtil.privateDecrypt(bytes, RSAUtil.string2PrivateKey(privateKey));
//
//        result = RSAUtil.byte2Base64(bytes);
//
//        System.out.println(result);
    }

}
