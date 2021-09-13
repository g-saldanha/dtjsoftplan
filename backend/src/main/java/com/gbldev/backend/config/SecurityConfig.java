package com.gbldev.backend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.gbldev.backend.security.ComercialUserDetailsService;

@Configuration
@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ComercialUserDetailsService userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/source").permitAll()
				.antMatchers("/pessoa").hasRole("USER")
				//				.antMatchers("/usuario").hasRole("USER")
				.antMatchers("/usuario/**").permitAll()
				.antMatchers("/login")
				.permitAll().antMatchers("/logout")
				.permitAll()
				.and().csrf().disable().headers().frameOptions().disable()
				.and().cors()
				.and().logout()
				.and().formLogin()
				.and().rememberMe();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * Configura o filtro de CORS globalmente para toda a aplicação.
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
