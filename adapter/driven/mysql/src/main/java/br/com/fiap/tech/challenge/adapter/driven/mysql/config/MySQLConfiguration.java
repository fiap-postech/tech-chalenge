package br.com.fiap.tech.challenge.adapter.driven.mysql.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories("br.com.fiap.tech.challenge.adapter.driven.mysql.repository")
@EntityScan("br.com.fiap.tech.challenge.adapter.driven.mysql.model")
@ComponentScan("br.com.fiap.tech.challenge.adapter.driven.mysql")
public class MySQLConfiguration {
}

