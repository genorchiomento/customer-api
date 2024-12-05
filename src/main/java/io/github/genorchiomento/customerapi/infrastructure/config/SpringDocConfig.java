package io.github.genorchiomento.customerapi.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer API")
                        .version("1.0")
                        .description("API para gerenciamento de clientes")
                        .contact(new Contact()
                                .name("Genor Chiomento")
                                .email("genor.chiomento@gmail.com")));
    }
}
