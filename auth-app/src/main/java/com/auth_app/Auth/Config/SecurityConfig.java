package com.auth_app.config;

import com.auth_app.dtos.ApiError;
import com.auth_app.security.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(AppConstants.AUTH_PUBLIC_URLS).permitAll()
                                .requestMatchers(HttpMethod.GET).hasRole(AppConstants.GUEST_ROLE)
                                .requestMatchers(".api/v1/users/**").hasRole(AppConstants.ADMIN_ROLE)
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 ->
                                oauth2
                                        .successHandler(successHandler)
                        // ❌ REMOVE THIS (NULL NOT ALLOWED)
                        // .failureHandler(null)
                )

                .logout(AbstractHttpConfigurer::disable)

                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint((request, response, exception) -> {

                                    response.setStatus(401);
                                    response.setContentType("application/json");

                                    String message = exception.getMessage();
                                    String error = (String) request.getAttribute("error");

                                    if (error != null) {
                                        message = error;
                                    }

                                    var apiError = ApiError.of(
                                            HttpStatus.UNAUTHORIZED.value(),
                                            "Unauthorized Access",
                                            message,
                                            request.getRequestURI(),
                                            true
                                    );

                                    var objectMapper = new ObjectMapper();
                                    response.getWriter().write(objectMapper.writeValueAsString(apiError));
                                })
                                .accessDeniedHandler((request, response, e) -> {
                                    response.setStatus(403);
                                    response.setContentType("application/json");
                                    String message = e.getMessage();
                                    String error = (String) request.getAttribute("error");
                                    if (error != null) {
                                        message = error;
                                    }
                                    var apiError = ApiError.of(
                                            HttpStatus.FORBIDDEN.value(),
                                            "Forbidden Access",
                                            message,
                                            request.getRequestURI(),
                                            true
                                    );
                                    var objectMapper = new ObjectMapper();
                                    response.getWriter().write(objectMapper.writeValueAsString(apiError));
                                })
                )
        // ✅ Now filter is NOT NULL
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}