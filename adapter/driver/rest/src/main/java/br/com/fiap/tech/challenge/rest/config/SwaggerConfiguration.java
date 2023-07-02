package br.com.fiap.tech.challenge.rest.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8688");
        devServer.setDescription("Server URL - Ambiente de Desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl("N/A");
        prodServer.setDescription("Server URL - Ambiente de Produção");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tech Challenge API")
                .version("1.0.0")
                .description("Esta API expõe endpoints referentes ao Tech Challenge de criação de um sistema de gerenciamento de pedidos de fast food.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
