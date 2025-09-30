package com.project.skillsync;

import com.project.skillsync.model.Role;
import com.project.skillsync.model.User;
import com.project.skillsync.repository.RoleRepository;
import com.project.skillsync.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SkillsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillsyncApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("password123"));

	}

}
