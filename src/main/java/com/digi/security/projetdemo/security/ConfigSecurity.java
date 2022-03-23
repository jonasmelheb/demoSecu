package com.digi.security.projetdemo.security;

import com.digi.security.projetdemo.dao.AppAuthProvider;
import com.digi.security.projetdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    /**
     * Je branche lmon service de type UserDetailsService
     * avec AuthenticationManagerBuilder
     * @param auth auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public AuthenticationProvider getProvider() {
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // All Authorised
        //http.csrf().disable().authorizeRequests().anyRequest().permitAll().and().httpBasic();
        http
//                .csrf().disable()
                .authenticationProvider(getProvider())
                .formLogin()
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").authenticated()
                .antMatchers("/api/**").permitAll() // AUTHORISE all controller REST
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // on n'envoie pas le formulaire de lohin aux controller REST

    }
}
