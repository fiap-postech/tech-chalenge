package br.com.fiap.tech.challenge.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("br.com.fiap.tech.challenge.rest")
@Import(RestModelMapperConfiguration.class)
public class RestConfiguration {
}
