package com.demo.JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserDetailsService customerUserDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, UserDetailsService customerUserDetailsService) {
        this.jwtFilter = jwtFilter;
        this.customerUserDetailsService = customerUserDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Replace with strong encoder in prod
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS with your config
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/user/login", "/user/signup", "/user/get", "/user/admin", 
                    "/user/forgotPassword", "/user/resetPassword", "/orders/get", 
                    "/orders/getByShopId/", "/orders/status/", 
                    "/category/get", "/category/add", "/category/update", 
                    "/category/delete/", "/product/get", "/product/add", 
                    "/product/update/", "/product/delete/", 
                    "/product/update-status/", "/deliveries/get", 
                    "/delivery/all", "/delivery/", "/delivery/status/", 
                    "/deliveries/add", "/images/", "/table-login/generate-qr/", 
                    "/table-login/add", "/table-login/auto-login/", 
                    "/orders/placeOrder", "/delivery/placeOrder", 
                    "/delivery/getByEmail", "/delivery/getOrderByShop/", 
                    "/bill/generate", "/table-login/edit/", 
                    "/table-login/all", "/table-login/status/", 
                    "/table-login/update-status/", "/shop/get", "/shop/all", 
                    "/shop/add", "/shop/update", "/shop/delete/", 
                    "/account/create", "/account/all", "/account/update/", 
                    "/account/delete/", "/account/login", "/account/id/", 
                    "/table-login/grouped-by-shop", "/table-login/shop/", 
                    "shop/shop-name/", "/product/shop/"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("https://frontend-alpha-gilt-12.vercel.app")); // Your frontend URL
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // **Important: use / here**
        return source;
    }
}

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ Apply CORS config
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/").permitAll()
//                 .anyRequest().authenticated()
//             );
//         return http.build();
//     }

//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration config = new CorsConfiguration();

//         // ✅ Use exact deployed frontend domain
//         config.setAllowedOrigins(List.of("https://frontend-alpha-gilt-12.vercel.app"));
//         config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         config.setAllowedHeaders(List.of("*"));
//         config.setAllowCredentials(true);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/", config);
//         return source;
//     }
// }




