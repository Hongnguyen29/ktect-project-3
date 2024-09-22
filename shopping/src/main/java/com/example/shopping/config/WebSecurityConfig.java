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
                            "/login",
                                    "/static/**",
                                    "/itemCategory")
                            .permitAll();
                    auth.requestMatchers("/register")
                            .anonymous();
                    auth.requestMatchers(
                            /*"/users/updateProfile",
                                    "/users/updateImage",
                                    "/users/profile",*/
                                    "/users/**")
                            .authenticated();


                    auth.requestMatchers(
                            /*"admin/openRequest/readAll",
                                    "/admin/openRequest/confirm/{requestId}",
                                    "/admin/closeRequest/confirm/{closeId}",
                                    "/admin/closeRequest/reaAll" , */
                                    "/admin/**"

                                    ).hasRole("ADMIN");

                    auth.requestMatchers("/openRequest") //
                            .hasRole("USER");

                    auth.requestMatchers(
                            /*"/orders/{productId}",
                                    "/orders/cancel/{orderId}",
                                    "/orders/userView",
                                    "/orders/view/{orderId}",*/
                                    "/orders/**")
                                    .hasAuthority("ORDER");

                    auth.requestMatchers(
                            /*"/shop/update",
                                    "/shop/closeRequest",
                                    "/shop/product",
                                    "/shop/{productId}",
                                    "/shop/accept/{orderId}",
                                    "/shop/orderView",*/
                                    "/shop/**")
                                    .hasRole("BUSINESS");

                    auth.requestMatchers(
                         /*   "/read/openRequest/{requestId}",
                                    "/read/closeRequest/{closeId}",*/
                                    "/read/**")
                            .hasAuthority("READ.REQUEST");


                    auth.requestMatchers(
                            /*"/view/shops",
                                    "/view/products",*/
                                    "/view/**")
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
