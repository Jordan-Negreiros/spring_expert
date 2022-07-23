package io.github.jordannegreiros.vendas.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfiguration {

    @Bean
    public CommandLineRunner execute() {
        return args -> System.out.println("DEV CONFIG");
    }
}
