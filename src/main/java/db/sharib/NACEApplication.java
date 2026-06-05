package db.sharib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NACEApplication {
	private static final Logger log = LoggerFactory.getLogger(NACEApplication.class);
	
	public static void main(String[] args) {
		log.info("Starting NACEApplication.");
		SpringApplication.run(NACEApplication.class, args);
	}
}
