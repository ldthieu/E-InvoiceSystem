package com.dxc.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/user/register");
//	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/service").authenticated()
                .antMatchers("/register", "/user/register").permitAll()
                .antMatchers("/","/invoice/**", "/invoice", "/chart", "/report", "invoice/report").hasRole("MEMBER")
                .antMatchers("/admin", "/user/state").hasRole("ADMIN")
                .and()
            .formLogin()
            	.loginPage("/login")
            	.usernameParameter("email")
            	.passwordParameter("password")
            	.defaultSuccessUrl("/")
            	.failureUrl("/login?error")
            	.and()
            	.csrf()
            	.ignoringAntMatchers("/user/register","/service/**", "/invoice/**", "/user/state", "invoice/report/**")
            	.and()
        	.exceptionHandling()
    			.accessDeniedPage("/403");
    }
	
}