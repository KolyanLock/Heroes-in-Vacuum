package com.kolyanlock.heroesinvacuum.configuration;

import com.kolyanlock.heroesinvacuum.service.auth.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@RequiredArgsConstructor
public class RESTSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/heroes/**").hasAnyRole("DEVELOPER", "MANAGER")
                .antMatchers("/api/**").hasRole("DEVELOPER")
                .antMatchers("/actuator/beans", "/actuator/mappings").hasRole("DEVELOPER")
                .antMatchers("/actuator/health", "/actuator/info").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
