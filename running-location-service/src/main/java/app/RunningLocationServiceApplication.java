package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by colt on 12/1/17.
 */

// this file is the enter point of our project
// this annotation will mark this as an spring boot application
@SpringBootApplication
public class RunningLocationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunningLocationServiceApplication.class);
    }
}
