package edu.alieninvasion.backend.controller.admin;

import edu.alieninvasion.backend.bean.RespBean;
import edu.alieninvasion.backend.bean.User;
import edu.alieninvasion.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserManaController {
    UserService userService;

    @Autowired
    public UserManaController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
    public RespBean deleteUserById(@PathVariable("uid") Long uid) {
        if (userService.deleteUserById(uid) == 1) {
            return new RespBean("success", "Deletion success!");
        } else {
            return new RespBean("error", "Deletion fail!");
        }
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public RespBean updateUserRole(Long role, Long id) {
        if (userService.updateUserRole(role, id) == 1) {
            return new RespBean("success", "Update success!");
        } else {
            return new RespBean("error", "Update fail!");
        }
    }
}