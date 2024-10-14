package dev.bootcamp.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log =
			LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		log.info("Application running!");

//		var helloWorld = new HelloWorld();
//		System.out.println(helloWorld.sayHelloWorld());
	}

//	@Bean
//	CommandLineRunner runner(
//	) {
//		return args -> {
//			Event event = new Event(1,"Konser A",
//					LocalDateTime.now(), LocalDateTime.now().plusHours(5),
//					1000, Location.JAKARTA);
//			log.info("Event: " + event);
//		};
//	}

}
