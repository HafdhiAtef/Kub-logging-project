package com.in28minutes.rest.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class scheduler {
    private static final Logger logger = LoggerFactory.getLogger(scheduler.class);

    @Scheduled(fixedRate = 100) // 60000 milliseconds = 1 minute
    public void logMessageEveryMinute() {
        // Add your log statement here
        //System.out.println("Logging a message every 1 minute");
        logger.info("Logging a message every 1 minute");
    }    
}
