package vn.kurisu.anime_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.kurisu.anime_service.entity.User;
import vn.kurisu.anime_service.repository.UserRepository;

import java.util.HashSet;

@SpringBootApplication
public class AnimeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner initDataBase (UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args ->
		{
			if (userRepository.findByUsername("admin") == null) {
				var roles = new HashSet<String>();
				roles.add("ADMIN");
				roles.add("USER");

				User admin = User.builder()
						.username("admin")
						.password(passwordEncoder.encode("admin123"))
						.email("admin@sg.vn")
						.roles(roles)
						.build();
				userRepository.save(admin);
				System.out.println("WARN: Da tao tai khoan admin mac dinh");

			}
		};
	}

}
