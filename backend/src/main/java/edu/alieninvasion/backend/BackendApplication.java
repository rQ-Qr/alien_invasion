package edu.alieninvasion.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * <h1>BackendApplication</h1>
 * The boot class of backend.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
public class BackendApplication {
    // start the project
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
