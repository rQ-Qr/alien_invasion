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

/**
 * <h1>UserController</h1>
 * This is a controller class to deal with request about user data.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@RestController
public class UserController {
    UserService userService;

    // constructor of the class
    @Autowired
    public UserController(UserService userService, DataStatisticsComponent dataStatisticsComponent) {
        this.userService = userService;
    }

    /**
     * This method is used to response the request of updating the score from client
     *
     * @param request the request from client
     * @return the response bean
     */
    @RequestMapping(value = "/client/score", method = RequestMethod.POST)
    public RespBean updateUserScore(HttpServletRequest request) {
        String data;
        try {
            // get the data from client
            data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new RespBean("error", "Fail!");
        }
        // parse the json data
        JSONObject jsonObject = JSONObject.parseObject(data);
        Long score = jsonObject.getLong("score");
        Long id = jsonObject.getLong("id");

        // success
        if (userService.updateUserScore(score, id) == 1) {
            return new RespBean("success", "Update success!");
        } else {
            return new RespBean("error", "Update fail!");
        }
    }

}