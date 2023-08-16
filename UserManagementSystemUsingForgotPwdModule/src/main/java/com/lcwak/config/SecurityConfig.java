package com.lcwak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	public CustomAuthSuccessHandler authSuccessHandler;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getDetailsService()
	{
		return new CustomUserDetailsService();
	}
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider()
	{
		DaoAuthenticationProvider daProvider=new DaoAuthenticationProvider();
		daProvider.setUserDetailsService(getDetailsService()); 
		daProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return daProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		/*http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/","register","/signin","saveuser").permitAll()
		.requestMatchers("/user/**").authenticated().and()
		.formLogin().loginPage("/signin").loginProcessingUrl("/userLogin")
		//.usernameParameter("email")
		.defaultSuccessUrl("/user/profile").permitAll();
		*/
		http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/**").permitAll().and()
		.formLogin().loginPage("/signin").loginProcessingUrl("/userLogin")
		.successHandler(authSuccessHandler)
		.and().logout().permitAll();
		
		return http.build();
	}
}
