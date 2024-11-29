package com.softdevsix.infrastructure.configuration.cors;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final CustomCorsConfiguration customCorsConfiguration;

    public SecurityConfig(CustomCorsConfiguration customCorsConfiguration) {
        this.customCorsConfiguration = customCorsConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterAutoConfiguration(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.anyRequest().permitAll())
                .cors(c -> c.configurationSource(customCorsConfiguration));
        return httpSecurity.build();
    }
}
