package com.menu.pubganalyzer.util.pubgAPI;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PubgAPIConfiguration {
    @Bean
    public PubgAPI pubgAPI(@Value("${util.pubg.api.token}") String token) {
        if (token == null || token.isBlank()) throw new BeanCreationException("property is null or blank [util.pubg.api.token]");
        return new DefaultPubgAPI(token, restTemplate());
    }

    private RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
        return new RestTemplate(clientHttpRequestFactory);
    }
}
