package com.omkaushik.restservice.restful_web_services.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//All requests should be authenticated
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		//If request is not authenticated a pop-up should be shown
		http.httpBasic(withDefaults());
		
		//Disable CSRF
		http.csrf(
				csrf -> csrf.disable()
				);		
		
		return http.build();
	}
	
}
