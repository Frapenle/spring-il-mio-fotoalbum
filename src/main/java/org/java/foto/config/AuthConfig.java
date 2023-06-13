package org.java.foto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		return http.csrf(c-> c.disable())
					.authorizeHttpRequests(a -> a
			        .requestMatchers("/admin/**").hasAuthority("ADMIN")
			        .requestMatchers("/admin/**").hasAnyAuthority("SUPERADMIN", "ADMIN")
			        .requestMatchers("/**").permitAll()
			).formLogin(f -> f
				.defaultSuccessUrl("/admin/foto")
				.permitAll()
			).logout(l -> l.logoutSuccessUrl("/")
			).build();
	}

}
