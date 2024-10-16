package dev.bootcamp.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log =
			LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		log.info("Application running!");

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "securepassword123";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println(encodedPassword);

//		var helloWorld = new HelloWorld();
//		System.out.println(helloWorld.sayHelloWorld());
	}


}
