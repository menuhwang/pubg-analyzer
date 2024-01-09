package com.menu.pubganalyzer.config;

import com.menu.pubganalyzer.support.admin.AdminOnly;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "${springdoc.server.url}")})
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .addOpenApiMethodFilter(method -> !method.isAnnotationPresent(AdminOnly.class))
                .build();
    }
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/**")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(AdminOnly.class))
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Battlestats API")
                        .description("Battlestat(PUBG Analyzer) API 명세서")
                        .version("beta")
                );
    }
}
