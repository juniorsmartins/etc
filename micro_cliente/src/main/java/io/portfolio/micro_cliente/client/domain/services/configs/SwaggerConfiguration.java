package io.portfolio.micro_cliente.client.domain.services.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Spring API")
                    .description("This is a rest api documentation.")
                    .version("v0.0.1")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

//    @Bean
//    public GroupedOpenApi userApi() {
//        final String[] packagesToScan = {"com.controller"};
//        return GroupedOpenApi
//                .builder()
//                .group("User API")
//                .packagesToScan(packagesToScan)
//                .pathsToMatch("/users/**")
//                .addOpenApiCustomiser(statusApiCostumizer())
//                .build();
//    }
//
//    private OpenApiCustomiser statusApiCostumizer() {
//        return openAPI -> openAPI
//                .info(new Info()
//                        .title("Springboot & OpenAPI")
//                        .description("This is a sample Spring Boot RESTful service using OpenAPI")
//                        .version("3.0.0");
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components())
//                .info(new Info().title("Contact Application API").description(
//                        "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
//    }
}
