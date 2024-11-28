package com.softdevsix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("Argos API")
                    .description("An application for verifying code coverage and quality of pull requests.")
                    .version("1.0")
                    .contact(new Contact()
                            .name("Argos Team")
                            .email("argos.team@gmail.com")));
  }
}
