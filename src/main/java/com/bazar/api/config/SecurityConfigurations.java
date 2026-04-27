package com.bazar.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ROTAS LIVRES
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/movimentacoes/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/roupas/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/roupas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/roupas/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/roupas/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").permitAll()

                        .anyRequest().authenticated()
                )
                .build();
    }
}