package edu.alieninvasion.backend.controller;

import edu.alieninvasion.backend.bean.RespBean;
import edu.alieninvasion.backend.bean.User;
import edu.alieninvasion.backend.service.DataStatisticsComponent;
import edu.alieninvasion.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService, DataStatisticsComponent dataStatisticsComponent) {
        this.userService = userService;
    }

    @RequestMapping(value = "/client/score", method = RequestMethod.POST)
    public RespBean updateUserScore(HttpServletRequest request) {
        String data;
        try {
            data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new RespBean("error", "Fail!");
        }
        JSONObject jsonObject = JSONObject.parseObject(data);
        Long score = jsonObject.getLong("score");
        Long id = jsonObject.getLong("id");
        System.out.println(id);
        System.out.println(score);

        if (userService.updateUserScore(score, id) == 1) {
            return new RespBean("success", "Update success!");
        } else {
            return new RespBean("error", "Update fail!");
        }
    }

}