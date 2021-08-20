package edu.alieninvasion.backend.mapper;

import edu.alieninvasion.backend.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h1>UserService</h1>
 * This is a mapper interface interacting with MyBatis and database.
 * It deals with user information.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@Mapper
public interface UserMapper {
    // get the user instance by username
    User loadUserByUsername(@Param("username") String username);
    // add a new user
    long reg(User user);
    // delete an user instance by user id
    int deleteUserById(Long uid);
    // update the user role by user id
    int setUserRole(@Param("role") Long role, @Param("id") Long id);
    // update the user score by user id
    int setUserScore(@Param("score") Long score, @Param("id") Long id);
    // get the user instance by user id
    User getUserById(@Param("id") Long id);
    // get the user list by user name
    List<User> getUserByUsername(@Param("username") String username);
    // add a new statistics instance at the 00:00 everyday
    void regStatisticsPerDay();
    // the number of sign in or sign up today + 1
    void updateStatistics();
    // get the date field in statistics table
    List<String> getDate();
    // get the number of sign in or sign up in recent seven days
    List<Integer> getDataStatistics();

}