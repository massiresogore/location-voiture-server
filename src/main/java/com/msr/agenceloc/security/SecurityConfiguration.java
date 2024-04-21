package com.msr.agenceloc.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Value("${api.endpoint.base-url}")
    private String baseUrl;
    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    public SecurityConfiguration(CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint) {
        this.customBasicAuthenticationEntryPoint = customBasicAuthenticationEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.GET,this.baseUrl+"/automobiles/**").permitAll()
                        .requestMatchers(HttpMethod.POST,this.baseUrl+"/automobiles/**").permitAll()
                        .requestMatchers(HttpMethod.GET,this.baseUrl+"/agences/**").permitAll()
                        .requestMatchers(HttpMethod.POST,this.baseUrl+"/agences").permitAll()
                        .requestMatchers(HttpMethod.PUT,this.baseUrl+"/agences/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH,this.baseUrl+"/agences/**").permitAll()
//                        .requestMatchers(HttpMethod.GET,this.baseUrl+"/users").permitAll()
                        .requestMatchers(HttpMethod.GET,this.baseUrl+"/users").hasAuthority("ROLE_admin")//protecting endpoint
                        //.requestMatchers(HttpMethod.POST,this.baseUrl+"/users").authenticated()//protecting endpoint
                        .requestMatchers(HttpMethod.POST,this.baseUrl+"/users").permitAll()
//                        .requestMatchers(HttpMethod.POST,this.baseUrl+"/users").hasAuthority("ROLE_admin")//protecting endpoint
                        .requestMatchers(HttpMethod.PUT,this.baseUrl+"/users/**").hasAuthority("ROLE_admin")//protecting endpoint
                        .requestMatchers(HttpMethod.DELETE,this.baseUrl+"/users/**").hasAuthority("ROLE_admin")//protecting endpoint
                        //.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()//autorise h2-console
                        //allow everything authenticated
                        .anyRequest().authenticated()//Recommand to put this at last

                )
                //.headers(AbstractHttpConfigurer::disable)
                //.headers(AbstractHttpConfigurer::disable)//autorise h2-console
                .csrf(AbstractHttpConfigurer::disable)//
                //.cors(Customizer.withDefaults())
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.customBasicAuthenticationEntryPoint))
                //.httpBasic(Customizer.withDefaults())
                //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .oauth2ResourceServer(oauth2ResourceServer-> oauth2ResourceServer
//                        .jwt(Customizer.withDefaults())
//                        .authenticationEntryPoint(this.customBearerTokenAuthenticationEntryPoint)
//                        .accessDeniedHandler(this.customBearerTokenAccessDeniedHandler)
//                )
                .sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
        //spring va nous aider vérifier l'authenticité du jeton JWT soumis
        //maintenant que nous utilison jwt pour l'authorisation et l'authentification
        //On doit désactivé la session
        //Ligne 74 dit a spring qu'il ne conserve pas de session
        // cela peut économiner beaucoup de ram lorsquil ya plusieurs utilisateur connecté
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }
}
