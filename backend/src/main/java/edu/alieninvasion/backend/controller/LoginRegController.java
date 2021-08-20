package edu.alieninvasion.backend.controller;

import edu.alieninvasion.backend.bean.RespBean;
import edu.alieninvasion.backend.bean.User;
import edu.alieninvasion.backend.service.DataStatisticsComponent;
import edu.alieninvasion.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <h1>LoginRegController</h1>
 * This is a controller class to deal with request about login and register.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@RestController
public class LoginRegController {
    UserService userService;
    DataStatisticsComponent dataStatisticsComponent;

    // constructor of the class
    @Autowired
    public LoginRegController(UserService userService, DataStatisticsComponent dataStatisticsComponent) {
        this.userService = userService;
        this.dataStatisticsComponent = dataStatisticsComponent;
    }

    /**
     * This method is used to response login request from frontend
     *
     * @param user the user instance
     * @return the response bean
     */
    @PostMapping("/login")
    public RespBean login(User user) {
        // success
        if (userService.login(user)==0) {
            // load user data
            User userDB = userService.loadUserByUsername(user.getUsername());
            // login number + 1
            userService.updateStatistics();
            return new RespBean("success", "Login Success!",
                    userDB.getId(), userDB.getRole(), userDB.getUsername(), userDB.getScore());
        }
        // Username or password is empty
        else if (userService.login(user)==1) {
            return new RespBean("error", "Username or password is empty.");
        }
        // Username does not exist
        else if (userService.login(user)==2) {
            return new RespBean("error", "Username does not exist.");
        }
        // Password is wrong
        else if (userService.login(user)==3) {
            return new RespBean("error", "Password is wrong.");
        }
        else
            return new RespBean("error", "Login Fail!");
    }

    /**
     * This method is used to response login request from client
     *
     * @param request the request from client
     * @return the response bean
     */
    @PostMapping("/client/login")
    public RespBean clientLogin(HttpServletRequest request) {
        String data;
        try {
            // get the json data from client
            data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new RespBean("error", "Fail!");
        }
        // parse the json
        User user = JSONObject.parseObject(data, User.class);
        return login(user);
    }

    /**
     * This method is used to response register request from frontend
     *
     * @param user the user instance
     * @return the response bean
     */
    @PostMapping("/reg")
    public RespBean reg(User user) {
        int result = userService.reg(user);
        // success
        if (result == 0) {
            // load user data
            User userDB = userService.loadUserByUsername(user.getUsername());
            // register number + 1
            userService.updateStatistics();
            return new RespBean("success", "Success!",
                    userDB.getId(), userDB.getRole(), userDB.getUsername(), userDB.getScore());
        }
        // Username exists
        else if (result == 2) {
            return new RespBean("error", "Username exists.");
        }
        // Username or password is empty
        else if (result == 1) {
            return new RespBean("error", "Username or password is empty.");
        } else {
            //fail
            return new RespBean("error", "Fail!");
        }
    }

    /**
     * This method is used to response register request from client
     *
     * @param request the request from client
     * @return the response bean
     */
    @PostMapping("/client/reg")
    public RespBean clientReg(HttpServletRequest request) throws IOException {
        String data;
        try {
            // get the json data from client
            data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new RespBean("error", "Fail!");
        }
        // parse the json
        User user = JSONObject.parseObject(data, User.class);
        return reg(user);
    }


}