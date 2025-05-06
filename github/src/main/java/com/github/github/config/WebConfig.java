package com.portfolio.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements   WebMvcConfigurer{


    //Alow this for react application
    @Override
    public void addCorsMappings(CorsRegistry registry) 
    {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*");
    }
    
}
