package com.guruguruzom.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.guruguruzom.security.handler.LoginFailureHandler;
import com.guruguruzom.security.handler.LoginSuccessHandler;

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired LoginSuccessHandler loginSuccessHandler;
	@Autowired LoginFailureHandler loginFailureHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/oauth/uncache_approvals", "/oauth/cache_approvals", "/images/**", "/error/*", "/js/**", "/css/**", "/img/**, /fonts/**", "/manual.zip");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/login", "/fonts/**", "/favicon.ico").permitAll()
			.antMatchers("/*.*").denyAll()
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
			.defaultSuccessUrl("/result")
			.successHandler(loginSuccessHandler)
			.failureHandler(loginFailureHandler)
		.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
		.and()
			.csrf().disable();
			;
	}

}
