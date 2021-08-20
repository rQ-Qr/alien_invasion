package edu.alieninvasion.backend.controller;

import edu.alieninvasion.backend.service.DataStatisticsComponent;
import edu.alieninvasion.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>StatisticsController</h1>
 * This is a controller class to deal with request about data statistics.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@RestController
public class StatisticsController {
    UserService userService;

    // constructor of the class
    @Autowired
    public StatisticsController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method is used to response statistics request from frontend
     *
     * @return the map storing the date and statistics data
     */
    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> date = userService.getDate();
        List<Integer> dataStatistics = userService.getDataStatistics();
        map.put("date", date);
        map.put("ds", dataStatistics);
        return map;
    }
}
