package com.authentication.authentication.Configuration;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuth jwtAuth;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuth jwtAuth) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuth = jwtAuth;
    }
    // Implement security config
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable csrf because we are using JWT (stateless)
          http.csrf(csrf -> csrf.disable())
                  .authorizeHttpRequests(auth -> auth
                                  .anyRequest().permitAll()
//                          .requestMatchers("/auth/**").permitAll()
//                          .anyRequest().authenticated()
                  )
                // We do not need session authentication, we are using JWT. Set to Stateless
                  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .authenticationProvider(authenticationProvider)
                  .addFilterBefore(jwtAuth, UsernamePasswordAuthenticationFilter.class)
                  .cors(withDefaults());

          return http.build();
    }
}
