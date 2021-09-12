package com.gbldev.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gbldev.backend.security.ComercialUserDetailsService;

@Configuration
@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ComercialUserDetailsService userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/source").permitAll()
				.antMatchers("/pessoa").hasRole("USER")
				.antMatchers("/usuario").hasRole("USER")
				.and()
				.csrf().disable()
				.headers().frameOptions().disable()
				.and()
				.rememberMe();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
		builder
				.userDetailsService(this.userDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
}
