package com.voucherturistico.payment.infrastructure.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info applicationInfo = new Info()
            .title("Payment Service")
            .description("Microserviço destinado a integração com payment gateways para pagamentos online")
            .version("1.0.0");

        return new OpenAPI()
            .info(applicationInfo);
    }

}
