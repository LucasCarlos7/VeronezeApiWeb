//package com.api.veroneze.application.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    SecurityFilter securityFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Desabilitar CSRF para APIs que não exigem proteção contra CSRF
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                // Configuração de permissões de acesso
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/login.html", "/styles.css", "/script.js", "/images/**").permitAll()
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permite o acesso ao Swagger sem autenticação
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .anyRequest().authenticated() // Exige autenticação para todas as outras requisições
//                )
//                .formLogin(login -> login
//                        .loginPage("/login-page")
//                        .permitAll()
//                )
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class); // Filtro JWT personalizado (se necessário)
//
//        return http.build();
//    }
//
////    @Autowired
////    SecurityFilter securityFilter;
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
////        return httpSecurity
////                .csrf(csrf -> csrf.disable())
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs.yaml").permitAll()
////                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
////                        .requestMatchers(HttpMethod.POST, "/funcionario/adicionar").permitAll()
////                        .anyRequest().permitAll() // Exige autenticação para os outros endpoints
////                )
////                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
////                .build();
////    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
