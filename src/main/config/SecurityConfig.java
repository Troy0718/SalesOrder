package main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@ComponentScan("main")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new SalesOrderAccessDeniedHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select login, password, enabled from users where login=?")
				.authoritiesByUsernameQuery("select login, role from roles where login=?");

//			.inMemoryAuthentication()
//			.withUser("john").password(passwordEncoder().encode("admin")).roles("ADMIN","EMPLOYEE" )
//			.and()
//			.withUser("Eric").password(passwordEncoder().encode("employee")).roles("EMPLOYEE")
//			.and()
//			.withUser("Micheal").password(passwordEncoder().encode("client")).roles("CLIENT");		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login").permitAll()
				.antMatchers("/addProducts", "/putCartDetail/**", "/showCart").hasAnyRole("ADMIN", "EMPLOYEE", "CLIENT") // .antMatchers("/putCartDetail/**").hasAnyRole("ADMIN"
																															// ,
																															// "EMPLOYEE"
																															// ,"CLIENT")
				.and().formLogin().loginPage("/login").loginProcessingUrl("/checkUserAccount").defaultSuccessUrl("/")
				.permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").invalidateHttpSession(true).permitAll().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler());
		// .exceptionHandling().accessDeniedPage("/forbidden");
	}
}
