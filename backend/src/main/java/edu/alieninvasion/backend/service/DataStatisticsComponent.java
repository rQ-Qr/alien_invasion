package edu.alieninvasion.backend.service;

import edu.alieninvasion.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DataStatisticsComponent {
    @Autowired
    UserService userService;

    //run everyday to count register
    @Scheduled(cron = "1 0 0 * * ?")
    public void regStatisticsPerDay() {
        userService.regStatisticsPerDay();
    }
}