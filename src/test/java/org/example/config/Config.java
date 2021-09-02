package org.example.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;



@Configuration
public class Config {
RestTemplate restTemplate ;


    @Bean
    public RestTemplate restTemplate()
    {
       restTemplate = new RestTemplate();
       return  restTemplate;
    }

}
