package edu.alieninvasion.backend.service;
import edu.alieninvasion.backend.bean.User;
import edu.alieninvasion.backend.mapper.CommentMapper;
import edu.alieninvasion.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * <h1>UserService</h1>
 * This is a service class implementing the CRUD of user data.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@Service
@Transactional
public class UserService {
    UserMapper userMapper;
    CommentMapper commentMapper;
    PasswordEncoder passwordEncoder;

    // construct of the class
    @Autowired
    public UserService(UserMapper userMapper, CommentMapper commentMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This method is used to get the user by username.
     *
     * @param username the name of the user
     * @return the user instance
     */
    public User loadUserByUsername(String username) {
        User user = userMapper.loadUserByUsername(username);
        // if no such an user, create a new one
        if (user == null) user = new User();
        return user;
    }

    /**
     * This method is used to log in
     *
     * @param user the user instance who require login
     * @return the login status
     */
    public int login(User user) {
        // user instance is empty
        if(user.getUsername().length()==0 || user.getPassword().length()==0) return 1;
        User userDB = loadUserByUsername(user.getUsername());
        // username does not exist
        if(userDB.getUsername()==null || userDB.getUsername().length()==0 ) return 2;
        // login success
        if (passwordEncoder.matches(user.getPassword(), userDB.getPassword())) return 0;
        // password is not correct
        else return 3;
    }

    /**
     * This method is used to register a new user
     *
     * @param user the user instance who require register
     * @return the register status
     */
    public int reg(User user) {
        // user instance is empty
        if(user.getUsername().length()==0 || user.getPassword().length()==0) return 1;
        User userDB = userMapper.loadUserByUsername(user.getUsername());
        // username exists
        if (userDB != null) return 2;
        // encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // set the register time
        user.setRegTime(new Timestamp(System.currentTimeMillis()));
        user.setScore((long)0);
        long result = userMapper.reg(user);
        // register success
        if (result == 1) return 0;
        // register fail
        else return 3;
    }

    /**
     * This method is used to delete an user
     *
     * @param uid the user id
     * @return the number of changed record
     */
    public int deleteUserById(Long uid) {
        if(userMapper.deleteUserById(uid)==1) {
            commentMapper.deleteCommentByUid(uid);
            return 1;
        }
        return 0;
    }

    /**
     * This method is used to update the user's highest score
     *
     * @param score the user's highest score
     * @param id the user's id
     * @return the number of changed record
     */
    public int updateUserScore(Long score, Long id) {
        return userMapper.setUserScore(score, id);
    }

    /**
     * This method is used to update the user's highest score
     *
     * @param role the user's role
     * @param id the user's id
     * @return the number of changed record
     */
    public int updateUserRole(Long role, Long id) {
        return userMapper.setUserRole(role, id);
    }

    /**
     * This method is used to update the user's highest score
     *
     * @param username the name of user
     * @return the list of users
     */
    public List<User> getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    /**
     * This method is used to get a user by id
     *
     * @param id the user's id
     * @return the user instance
     */
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    /**
     * This method is used to add a new statistics record
     *
     */
    public void regStatisticsPerDay() {
        userMapper.regStatisticsPerDay();
    }

    /**
     * This method is used to add a sign in or signup count
     *
     */
    public void updateStatistics() {
        userMapper.updateStatistics();
    }

    /**
     * This method is used to get the latest date field in statistics table
     *
     * @return the list of date
     */
    public List<String> getDate() {
        return userMapper.getDate();
    }

    /**
     * This method is used to get the latest count field in statistics table
     *
     * @return the list of count
     */
    public List<Integer> getDataStatistics() {
        return userMapper.getDataStatistics();
    }
}