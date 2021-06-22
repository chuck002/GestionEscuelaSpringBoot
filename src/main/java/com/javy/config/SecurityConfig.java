package com.javy.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passEncoder)
		.usersByUsernameQuery("SELECT username_user, password_user, enabled FROM `users` WHERE username_user=?")
		.authoritiesByUsernameQuery("SELECT u.username_user, r.roles FROM roles r INNER JOIN users u ON r.user_id=u.id WHERE u.username_user=?");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/index","/","/home").hasAnyRole("ADMIN","STUDENT")
		.antMatchers("/admin/teacher/listar").hasAnyRole("ADMIN")
		.antMatchers("/admin/teacher/listar/**").hasAnyRole("ADMIN")
		.antMatchers("/admin/teacher/update").hasAnyRole("ADMIN")
		.antMatchers("/admin/teacher/delete/**").hasAnyRole("ADMIN")
		.antMatchers("/admin/teacher/add").hasAnyRole("ADMIN")
		.antMatchers("/admin/course/listar").hasAnyRole("ADMIN")
		.antMatchers("/admin/course/update").hasAnyRole("ADMIN")
		.antMatchers("/admin/course/delete/{id}").hasAnyRole("ADMIN")
		.antMatchers("/admin/course/add").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403");
		
		 
	}

}
