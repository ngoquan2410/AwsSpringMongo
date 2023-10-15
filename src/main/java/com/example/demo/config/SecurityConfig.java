package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	@Autowired
	private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

	@Autowired
	public CustomUserDetailsService userDetailsService;

	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http 
			.authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers("/home").permitAll()
	        .antMatchers("/login").permitAll()
	        .antMatchers("/signup").permitAll()
	        .antMatchers("/dashboard/**").hasAuthority("ADMIN")
	        .antMatchers("/role/*").hasAuthority("ADMIN").anyRequest()
	        .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
	        .loginPage("/login").failureUrl("/login?error=true")
	        .usernameParameter("email")
	        .passwordParameter("password")
	        .and().logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        .logoutSuccessUrl("/").and().exceptionHandling();

		return http.build();
	}
}
