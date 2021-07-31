package com.poll.app.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SpringSecurityWebAuxTestConfig {


    @Bean
    @Primary
    public UserDetailsService userDetailsService(){

        return null;
    }
}
