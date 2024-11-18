package ca.gbc.apigateway.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${apigateway-service.version}")
    private String version;

    @Bean
    public OpenAPI apigatewayServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("api-gateway Service API")
                        .description("This is the REST API for api-gateway Service")
                        .version(version)
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("api-gateway Service Wiki Documentation")
                        .url("https://mycompany.ca/apigateway-service/docs"));
    }
}
