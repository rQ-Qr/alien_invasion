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

@RestController
public class LoginRegController {
    UserService userService;
    DataStatisticsComponent dataStatisticsComponent;

    @Autowired
    public LoginRegController(UserService userService, DataStatisticsComponent dataStatisticsComponent) {
        this.userService = userService;
        this.dataStatisticsComponent = dataStatisticsComponent;
    }

    @PostMapping("/login")
    public RespBean login(User user) {
        if (userService.login(user)==0) {
            User userDB = userService.loadUserByUsername(user.getUsername());
            userService.updateStatistics();
            return new RespBean("success", "Login Success!",
                    userDB.getId(), userDB.getRole(), userDB.getUsername(), userDB.getScore());
        }
        else if (userService.login(user)==1) {
            return new RespBean("error", "Username or password is empty.");
        }
        else if (userService.login(user)==2) {
            return new RespBean("error", "Username does not exist.");
        }
        else if (userService.login(user)==3) {
            return new RespBean("error", "Password is wrong.");
        }
        else
            return new RespBean("error", "Login Fail!");
    }

    @PostMapping("/client/login")
    public RespBean clientLogin(HttpServletRequest request) {
        String data;
        try {
            data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new RespBean("error", "Fail!");
        }
        User user = JSONObject.parseObject(data, User.class);
        return login(user);
    }

    @PostMapping("/reg")
    public RespBean reg(User user) {
        int result = userService.reg(user);
        if (result == 0) {
            User userDB = userService.loadUserByUsername(user.getUsername());
            userService.updateStatistics();
            return new RespBean("success", "Success!",
                    userDB.getId(), userDB.getRole(), userDB.getUsername(), userDB.getScore());
        } else if (result == 2) {
            return new RespBean("error", "Username exists.");
        } else if (result == 1) {
            return new RespBean("error", "Username or password is empty.");
        } else {
            //失败
            return new RespBean("error", "Fail!");
        }
    }

    @PostMapping("/client/reg")
    public RespBean clientReg(HttpServletRequest request) throws IOException {
        String data;
        try {
            data = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new RespBean("error", "Fail!");
        }
        User user = JSONObject.parseObject(data, User.class);
        return reg(user);
    }


}