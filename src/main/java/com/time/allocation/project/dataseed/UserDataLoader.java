package com.time.allocation.project.dataseed;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.time.allocation.project.entity.User;
import com.time.allocation.project.repository.UserRepository;
import com.time.allocation.project.service.UserService;

@Component
public class UserDataLoader implements CommandLineRunner {

	private final UserRepository userRepository;

	private final UserService userService;

	
	public UserDataLoader(UserService userService, UserRepository userRepository)
	{
		this.userService = userService;
		this.userRepository = userRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		loadUserData();
	}

	private void loadUserData() {
		if (userRepository.count() == 0) {
			User user = new User(null, "admin", "a@email.com", "admin", "password", Collections.emptyList());
			userService.save(user);
		}
	}
}
