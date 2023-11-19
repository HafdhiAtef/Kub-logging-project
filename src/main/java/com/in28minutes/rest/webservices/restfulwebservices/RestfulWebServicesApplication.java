package com.in28minutes.rest.webservices.restfulwebservices;

//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;



@SpringBootApplication
@EnableScheduling
public class RestfulWebServicesApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestfulWebServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
		
		//LOGGER.info("Simple log statement with inputs {}, {} and {}", 1,2,3);
	
	}
	@Scheduled(fixedDelay = 3000) // 60000 milliseconds = 1 minute
    public void logMessageEveryMinute() {
        // Add your log statement here
        //System.out.println("Logging a message every 1 minute");
        LOGGER.info("Logging a message every 30 seconds");
    }
}
