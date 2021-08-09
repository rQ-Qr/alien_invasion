package edu.alieninvasion.backend.mapper;

import edu.alieninvasion.backend.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(@Param("username") String username);

    long reg(User user);

    int deleteUserById(Long uid);

    int setUserRole(@Param("role") Long role, @Param("id") Long id);

    User getUserById(@Param("id") Long id);

    List<User> getUserByUsername(@Param("username") String username);

    void regStatisticsPerDay();

    void updateStatistics();

    List<String> getDate();

    List<Integer> getDataStatistics();

}