package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/csrf").permitAll()
						.requestMatchers("/moneySend").permitAll()
						.requestMatchers("/xss").permitAll()
						.requestMatchers("/xssresult").permitAll()
						.requestMatchers("/sqlinjection").permitAll()
						.requestMatchers("/myreview").permitAll()
						.requestMatchers("/directory/**").denyAll());
		return http.build();
	}
}