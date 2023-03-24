//package org.indexmonitor.auth.application.filterConfigs;
//
//
//import org.indexmonitor.auth.application.authenticationProviders.EmailPasswordAuthenticationProvider;
//import org.indexmonitor.auth.application.authenticationProviders.EmailVerificationCodeAuthenticationProvider;
//import org.indexmonitor.auth.application.authenticationProviders.UsernamePasswordAuthenticationProvider;
//import org.indexmonitor.auth.application.entryPoints.CustomAuthenticationEntryPoint;
//import org.indexmonitor.auth.application.filters.EmailPasswordAuthenticationFilter;
//import org.indexmonitor.auth.application.filters.EmailVerificationCodeAuthenticationFilter;
//import org.indexmonitor.auth.application.filters.UsernamePasswordAuthenticationFilter;
//import org.indexmonitor.auth.application.services.AppUserDetailsService;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.context.SecurityContextHolderFilter;
//
//@Configuration
//@AllArgsConstructor
//public class LoginFilterChainConfig {
//
//    private final AppUserDetailsService userAccountDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    public SecurityFilterChain loginSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests().requestMatchers("/login").permitAll()
//                .and()
//                .authenticationManager(loginProviderManager())
//                .addFilterAfter(new EmailPasswordAuthenticationFilter(loginProviderManager()), SecurityContextHolderFilter.class)
//                .addFilterAt(new UsernamePasswordAuthenticationFilter(loginProviderManager()), EmailPasswordAuthenticationFilter.class)
//                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//        return http.build();
//    }
//
//    @Bean
//    public EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider(){
//        EmailPasswordAuthenticationProvider authenticationProvider = new EmailPasswordAuthenticationProvider();
//        authenticationProvider.setAppUserDetailsService(userAccountDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }
//
//    @Bean
//    public EmailVerificationCodeAuthenticationProvider emailVerificationCodeAuthenticationProvider(){
//        EmailVerificationCodeAuthenticationProvider authenticationProvider = new EmailVerificationCodeAuthenticationProvider();
//        authenticationProvider.setAppUserDetailsService(userAccountDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }
//
//    @Bean
//    public UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider(){
//        UsernamePasswordAuthenticationProvider authenticationProvider = new UsernamePasswordAuthenticationProvider();
//        authenticationProvider.setAppUserDetailsService(userAccountDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }
//
//    @Bean
//    public ProviderManager loginProviderManager(){
//        ProviderManager providerManager = new ProviderManager(
//                emailPasswordAuthenticationProvider(),
//                usernamePasswordAuthenticationProvider()
//                );
//
//        return providerManager;
//    }
//}
