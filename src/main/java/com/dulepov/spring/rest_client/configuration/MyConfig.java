package com.dulepov.spring.rest_client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.dulepov.spring.rest_client")
public class MyConfig {

    // класс для выполенения http-запросов к REST Server программно:
    @Bean
    public RestTemplate restTemplate(){
//        return new RestTemplate();
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

}
