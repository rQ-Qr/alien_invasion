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

/**
 * <h1>UserManaController</h1>
 * This is a controller class to deal with request about user management.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */
@RestController
@RequestMapping("/admin")
public class UserManaController {
    UserService userService;
    // Constructor of the class
    @Autowired
    public UserManaController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method is used to get the user instance by username
     *
     * @param username the name of user
     * @return the list of users
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * This method is used to get the user instance by user id
     *
     * @param id the id of user
     * @return the user instance
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * This method is used to delete the user instance by the user id
     *
     * @param uid the id of user
     * @return the user instance
     */
    @RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
    public RespBean deleteUserById(@PathVariable("uid") Long uid) {
        // deletion success
        if (userService.deleteUserById(uid) == 1) {
            return new RespBean("success", "Deletion success!");
        } else {
            return new RespBean("error", "Deletion fail!");
        }
    }

    /**
     * This method is used to update the user role by user id
     *
     * @param role the role of user
     * @param id the id of user
     * @return the user instance
     */
    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public RespBean updateUserRole(Long role, Long id) {
        // update success
        if (userService.updateUserRole(role, id) == 1) {
            return new RespBean("success", "Update success!");
        } else {
            return new RespBean("error", "Update fail!");
        }
    }
}