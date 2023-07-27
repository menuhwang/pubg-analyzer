package com.menu.pubganalyzer.support.fixture.admin;

import com.menu.pubganalyzer.support.admin.AdminConfig;
import com.menu.pubganalyzer.support.admin.AdminProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({AdminConfig.class, AdminProperties.class})
public @interface AdminEnvTest {
}
