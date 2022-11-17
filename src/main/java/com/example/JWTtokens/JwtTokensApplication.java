package com.example.JWTtokens;

import com.example.JWTtokens.domain.AppUser;
import com.example.JWTtokens.domain.Role;
import com.example.JWTtokens.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtTokensApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokensApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AppUserService appUserService) {
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null, "ROLE_MANAGER"));
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			appUserService.saveUser(new AppUser(null, "John Brian", "john", "1234", new ArrayList<>()));
			appUserService.saveUser(new AppUser(null, "Brian Mutethia", "brian", "1234", new ArrayList<>()));
			appUserService.saveUser(new AppUser(null, "Daniel William", "daniel", "1234", new ArrayList<>()));
			appUserService.saveUser(new AppUser(null, "Calvin Duke", "calvin", "1234", new ArrayList<>()));

			appUserService.addRoleToUser("john", "ROLE_USER");
			appUserService.addRoleToUser("john", "ROLE_MANAGER");
			appUserService.addRoleToUser("brian", "ROLE_MANAGER");
			appUserService.addRoleToUser("daniel", "ROLE_ADMIN");
			appUserService.addRoleToUser("calvin", "ROLE_SUPER_ADMIN");
			appUserService.addRoleToUser("calvin", "ROLE_ADMIN");
			appUserService.addRoleToUser("calvin", "ROLE_USER");

		};
	}

}
