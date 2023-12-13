package com.menu.pubganalyzer.support.feign;

import com.menu.pubganalyzer.config.FeignConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@Import({FeignConfig.class, FeignClientTestConfig.class})
@SpringBootTest(
        properties = {"feign.client.config.default.loggerLevel=FULL"},
        classes = FeignClientsConfiguration.class)
public @interface FeignClientTest {
}
