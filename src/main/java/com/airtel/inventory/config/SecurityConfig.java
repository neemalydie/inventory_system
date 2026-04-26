package com.airtel.inventory.config;

import com.airtel.inventory.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                // Public access - no login required for these paths
                .antMatchers("/", "/index", "/index.html", "/login", "/css/**", "/js/**", "/error").permitAll()
                // API endpoints - require ADMIN role
                .antMatchers("/api/users/**").hasRole("ADMIN")
                // All other requests require authentication
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
            .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?expired");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Try database users first
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
        
        // In-memory backup users (in case database is empty)
        auth.inMemoryAuthentication()
            .withUser("24RP00844")
            .password(passwordEncoder().encode("24RP06514"))
            .roles("ADMIN")
            .and()
            .withUser("24RP06514")
            .password(passwordEncoder().encode("24RP00844"))
            .roles("STAFF");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}