package io.luigicuri.lanches.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails usuarioAdm = User.builder()
                .username("admin")
                .password(encoder().encode("lanches"))
                .roles("ADMINISTRADOR")
                .build();
        return new InMemoryUserDetailsManager(usuarioAdm);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/api/lanches").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/api/lanches").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/lanches/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/lanches/**").hasRole("ADMINISTRADOR")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
