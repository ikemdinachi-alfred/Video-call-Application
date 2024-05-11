package com.alfredTech.ATMeeet;

import com.alfredTech.ATMeeet.user.User;
import com.alfredTech.ATMeeet.userServices.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ATVideoCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ATVideoCallApplication.class, args);
	}
@Bean
	public CommandLineRunner commandLineRunner(
			UserService userService
){
		return args -> {
			userService.registerUser(User.builder()
							.username("alfred")
							.email("alfred@gmail.com")
							.password("pass")
					.build());
			userService.registerUser(User.builder()
					.username("miracle")
					.email("miracle@gmail.com")
					.password("password")
					.build());
		};
}
}
