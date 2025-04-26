package com.pixelo.pixelo.Controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

//    @Override
//    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//        // Create a custom JsonFactory with stream read constraints
//        JsonFactory factory = JsonFactory.builder()
//                .streamReadConstraints(StreamReadConstraints.builder().maxStringLength(90000000).maxNumberLength(1000).maxNestingDepth(1000).build()).build();
////                .streamReadConstraints(StreamReadConstraints.builder()
////                        .maxStringLength(500_00_000)  // Example large string length limit
////                        .build())
////                .build();
//
//        // Create a custom ObjectMapper using the custom JsonFactory
//        ObjectMapper customMapper = JsonMapper.builder(factory).build();
//
//        // Configure the Jackson2ObjectMapperBuilder with the custom ObjectMapper
//        jacksonObjectMapperBuilder.configure(customMapper);
//    }
}
