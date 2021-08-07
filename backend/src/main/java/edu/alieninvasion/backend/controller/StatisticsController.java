package edu.alieninvasion.backend.controller;

import edu.alieninvasion.backend.service.DataStatisticsComponent;
import edu.alieninvasion.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController {
    UserService userService;

    @Autowired
    public StatisticsController(UserService userService) {
        this.userService = userService;
    }

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
