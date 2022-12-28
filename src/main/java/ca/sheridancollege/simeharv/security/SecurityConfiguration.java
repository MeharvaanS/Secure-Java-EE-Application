package ca.sheridancollege.simeharv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//			.antMatchers("/index").permitAll()
//			.antMatchers("/").permitAll() 
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/employee/**").hasRole("EMPLOYEE")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("userA").password("aaaa").roles("ADMIN")
			.and()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("userB").password("bbbb").roles("EMPLOYEE")
			.and()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("userC").password("cccc").roles("ADMIN", "EMPLOYEE");
			}
}