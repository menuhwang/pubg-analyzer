package com.menu.pubganalyzer.support.feign;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientTestConfig {
    @Bean
    public Decoder feignDecoder() {

        ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;
        return new SpringDecoder(messageConverters);
    }
}
