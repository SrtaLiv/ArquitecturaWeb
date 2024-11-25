package com.microservicioparada.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Parada")
                .pathsToMatch("/paradas/**")
                .build();
    }

    @Bean("OpenAPI")
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                                          @Value("${application-version}") String version) {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("Parada API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Parada API Licence")));
    }
}
