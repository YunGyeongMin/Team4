package kr.gudi.config.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf().disable()
		    .authorizeRequests()
				.antMatchers("/", "/res/**", "/lib/**", "/signup", "/myList/**", "/profile/**", "/page/**").permitAll()
//				.antMatchers("/admin").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.successForwardUrl("/security/success")
				.failureForwardUrl("/security/error")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
//				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/security/denied");
	}

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("1234").roles("ADMIN")
//			.and()
//			.withUser("gudi").password("1").roles("USER");
		
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery("select num, password,enabled "
	    	        			+ "from b_users "
	    	        		   + "where email = ?")
	       .authoritiesByUsernameQuery("select num,authority "
	    	        				   + "from b_authorities "
	    	        				  + "where num = ?");
		
	}
	
}
