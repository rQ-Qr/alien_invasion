package edu.alieninvasion.backend.service;

import edu.alieninvasion.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <h1>DataStatisticsComponent</h1>
 * This is a service class implementing the auto creation of data statistics everyday.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@Component
public class DataStatisticsComponent {
    @Autowired
    UserService userService;

    //run everyday to count register
    // add a new statistics record everyday
    @Scheduled(cron = "1 0 0 * * ?")
    public void regStatisticsPerDay() {
        userService.regStatisticsPerDay();
    }
}