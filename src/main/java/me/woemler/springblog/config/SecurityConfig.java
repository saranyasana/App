package me.woemler.springblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import me.woemler.springblog.repositories.UserRepository;

/**
 * @author woemler
 * 
*/

@Configuration
@EnableWebSecurity
@Import(DataSourceConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private UserRepository userRepository;
	

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userRepository)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/","/static/**","/registration","/home","/error").permitAll()
				.antMatchers("/blog/**", "/about","/media").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/admin")
				.failureUrl("/login")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login");
	}

}
