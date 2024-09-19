package com.example.shopping.config;

import com.example.shopping.jwt.JwtTokenFilter;
import com.example.shopping.jwt.JwtTokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    private final JwtTokenUtils tokenUtils;
    private final UserDetailsService detailsService;

    public WebSecurityConfig(
            JwtTokenUtils tokenUtils,
            UserDetailsService detailsService
    ){
        this.tokenUtils = tokenUtils;
        this.detailsService = detailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            "/users/login",
                                    "/static/**",
                                    "/itemCategory")
                            .permitAll();
                    auth.requestMatchers("/users/register")
                            .anonymous();
                    auth.requestMatchers(
                            "/users/updateProfile",
                                    "/users/updateImage",
                                    "/users/profile")
                            .authenticated();


                    auth.requestMatchers("/openRequest/readAll",
                                    "/openRequest/confirm/{requestId}",
                                    "/closeRequest/confirm/{closeId}",
                                    "/closeRequest/reaAll")
                            .hasRole("ADMIN");

                    auth.requestMatchers("/openRequest")
                            .hasRole("USER");

                    auth.requestMatchers(
                            "/orders/{productId}",
                                    "orders/delete/{orderId}",
                                    "orders/user")
                                    .hasAuthority("ORDER");

                    auth.requestMatchers(
                            "/shops/update",
                                    "/closeRequest",
                                    "/shops/product",
                                    "/shops/{productId}",
                                    "/orders/accept/{orderId}",
                                    "orders/shop")
                                    .hasRole("BUSINESS");

                    auth.requestMatchers(
                            "/openRequest/{requestId}",
                                    "/closeRequest/{closeId}")
                            .hasAuthority("READ.REQUEST");


                    auth.requestMatchers(
                            "/shops/view",
                                    "/shops/products")
                            .hasAuthority("VIEW");

                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .addFilterBefore(
//                        new AlwaysAuthenticatedFilter(),
                        new JwtTokenFilter(
                                tokenUtils,
                                detailsService
                        ),
                        AuthorizationFilter.class
                )
        ;
        return http.build();
    }
}
