package in.learnhub.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	@Autowired
//	public CustomAuthSuccessHandler successHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getDetailsService() {
		
		return new CustomUserDetailsService();
		
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
		
	}
	
	
	
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//	    http.csrf(csrf -> csrf.disable())
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/user/**").authenticated()
//	            .anyRequest().permitAll()
//	        )
//	        .formLogin(form -> form
//	            .loginPage("/customlogin")
//	            .loginProcessingUrl("/userlogin")
//	            .defaultSuccessUrl("/user/profile")
//	            .permitAll()
//	        );
//
//	    return http.build();
//	}

	
	    
	    private final CustomAuthSuccessHandler successHandler;

	    // Constructor injection of the success handler
	    public SecurityConfig(CustomAuthSuccessHandler successHandler) {
	        this.successHandler = successHandler;
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
				    .requestMatchers("/Tools/**", "/comingsoon/**","/pdf/**","/pdfviewer/**","/snapshot/**","/computer_courses/**","/quiz/**").permitAll()
	                .requestMatchers("/admin/**").hasRole("ADMIN")
	                .requestMatchers("/user/**").hasRole("NORMAL")
	                .anyRequest().permitAll()
	            )
	            .formLogin(form -> form
	                .loginPage("/customlogin")
	                .loginProcessingUrl("/userlogin")
	                .successHandler(successHandler)  // Use the successHandler bean
	                .permitAll()
	            ).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
				);

	        return http.build();
	    }
	










	}
