package org.example.service;


import org.example.config.Config;
import org.example.utils.PropertiesLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@ContextConfiguration(classes = Config.class)
public class WeatherForecastImpl extends AbstractTestNGSpringContextTests {

    @Autowired
    RestTemplate restTemplate;

    HttpHeaders headers;
    HttpEntity<String> entity;
    UriComponentsBuilder builder;



    public void setHeader() {
        headers = new HttpHeaders();
        headers.set("Accept", "application/json");
    }


    public String getWeatherForecastDetailsByLocation(String city, String stateCode, String CountryCode) throws IOException {
        getURL(PropertiesLoad.getPropertyValue("url.api", getClass().getClassLoader().getResourceAsStream("integrationTest.properties")), city, stateCode, CountryCode);
        ResponseEntity<Map> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Map.class);
        HashMap<String,Object> mp = (HashMap<String, Object>) response.getBody().get("main");
        return  String.valueOf(mp.get("temp"));
    }

    private UriComponentsBuilder getURL(String endpoint, String city, String stateCode, String CountryCode) throws IOException {
        setHeader();
        entity = new HttpEntity<>(headers);
        String location = city + "," + stateCode + "," + CountryCode;
        builder = UriComponentsBuilder.fromUriString(endpoint);
        builder.queryParam("q", location);
        builder.queryParam("appid", PropertiesLoad.getPropertyValue("api.key", getClass().getClassLoader().getResourceAsStream("integrationTest.properties")));
        return builder;
    }
}
