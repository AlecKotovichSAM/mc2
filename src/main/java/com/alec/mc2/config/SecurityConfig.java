package com.alec.mc2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${my.admin.password}")
	private String adminPwd;

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User
				.withUsername("admin")
				.password(passwordEncoder().encode(adminPwd))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.authorizeRequests()
				.requestMatchers("/admin/**")
				.hasRole("ADMIN")
				.requestMatchers("/**")
				.permitAll()
				.anyRequest()
				.anonymous()
//				.authenticated()
				.and()
				.formLogin()
//				.loginPage("/login")
//				.loginProcessingUrl("/perform_login");
				;

		return http.build();
	}

}
