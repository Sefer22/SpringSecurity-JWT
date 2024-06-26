package com.example.securityjwt;

import com.example.securityjwt.entity.User;
import com.example.securityjwt.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class SecurityJwtApplication {

	private final UserRepository userRepository;

	@PostConstruct
	public void createUsers() {
		List<User> users = Stream.of(
				new User(1,"safar","12345","safar@gmailcom"),
				new User(1,"tunzala","12345","tunzala@gmailcom"),
				new User(1,"farac","12345","farac@gmailcom"),
				new User(1,"rovnaq","12345","rovnaq@gmailcom")
		).collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	public static void main(String[] args) {

		SpringApplication.run(SecurityJwtApplication.class, args);
	}

}
