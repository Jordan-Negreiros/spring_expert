package io.github.jordannegreiros.vendas.configuration;

import io.github.jordannegreiros.vendas.annotation.DevProfile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@DevProfile
public class DevConfiguration {

    @Bean
    public CommandLineRunner execute() {
        return args -> System.out.println("DEV CONFIG");
    }
}

