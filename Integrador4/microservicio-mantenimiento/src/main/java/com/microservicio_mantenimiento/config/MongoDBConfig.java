package com.microservicio_mantenimiento.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.microservicio_mantenimiento.repository.mongo")
public class MongoDBConfig {

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost:27017/mantenimientomongodb?authSource=admin");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "mantenimientomongodb");
    }
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("mantenimiento")
                .pathsToMatch("/mantenimiento/**")
                .build();
    }

    @Bean("OpenAPI")
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                                          @Value("${application-version}") String version) {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("Mantenimiento API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Mantenimiento API Licence")));
    }
}
