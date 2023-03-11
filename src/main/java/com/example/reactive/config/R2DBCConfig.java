package com.example.reactive.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class R2DBCConfig {
    @Value("${MARIA_USER}")
    private String user;
    @Value("${MARIA_PASSWORD}")
    private String password;
    @Value("${MARIA_DRIVER}")
    private String driver;
    @Value("${MARIA_HOST}")
    private String host;
    @Value("${MARIA_DATABASE}")
    private String database;
    @Value("${MARIA_PORT}")
    private String port;

    @Bean(name = "mariaConnectionFactory")
    @Primary
    @ConfigurationProperties(prefix="spring.r2dbc")
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, driver)
                        .option(HOST, host)
                        .option(USER, user)
                        .option(PORT, Integer.valueOf(port))
                        .option(PASSWORD, password)
                        .option(DATABASE, database)
                        .build());
    }
}
