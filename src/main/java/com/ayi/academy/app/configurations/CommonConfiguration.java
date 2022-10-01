package com.ayi.academy.app.configurations;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.ayi.academy.app.configurations"
        +"com.ayi.academy.app.services"
        +"com.ayi.academy.app.mappers"
        +"com.ayi.academy.app.repositories"})
public class CommonConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
