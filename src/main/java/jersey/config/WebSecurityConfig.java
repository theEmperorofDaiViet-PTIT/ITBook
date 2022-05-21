package jersey.config;

import jersey.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/account/accountInfo","/account/shoppingCartConfirmation")
		.access("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE', 'ROLE_MANAGER')");

		http.authorizeRequests().antMatchers("/account/admin/orderList", "/account/admin/order")
				.access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");

		http.authorizeRequests().antMatchers("/account/admin/product").access("hasRole('ROLE_MANAGER')");
		
		http.authorizeRequests().antMatchers("/account/customer/orderList","/account/customer/order").access("hasRole('ROLE_CUSTOMER')");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().and().formLogin()

				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/account/login")
				.defaultSuccessUrl("/account/accountInfo")
				.failureUrl("/account/login?error=true")
				.usernameParameter("userName")
				.passwordParameter("password")

				.and().logout().logoutUrl("/account/logout").logoutSuccessUrl("/");

	}
}