package com.example.bookstore.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BookStoreSecurityConfig {
    @Bean
    UserDetailsManager userDetailsManager(DataSource DataSource) {
      return new JdbcUserDetailsManager(DataSource);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.authorizeHttpRequests(configurer ->
                configurer
                          .requestMatchers("/").hasAnyRole("MEMBER", "ADMIN")
                          .requestMatchers("/member/**").hasRole("MEMBER")
                          .requestMatchers("/admin/**").hasRole("ADMIN")
                          .requestMatchers("/css/**", "/img/**").permitAll()
                          .requestMatchers("/actuator/**").hasRole("ADMIN")
                          .anyRequest().authenticated()
    )
                .formLogin(form ->
                      form.loginPage("/login")
                          .loginProcessingUrl(("/authenticateTheUser"))
                          .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                            configurer.accessDeniedPage("/access-denied")
                );
    return http.build();
  }
}
