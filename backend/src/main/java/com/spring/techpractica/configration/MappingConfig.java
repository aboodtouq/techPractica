package com.spring.techpractica.configration;

import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.maper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    @Bean
    public SessionMapper sessionMapper() {
        return new SessionMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }


}
