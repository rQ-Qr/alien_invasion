package edu.alieninvasion.backend.service;
import edu.alieninvasion.backend.bean.User;
import edu.alieninvasion.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class UserService {
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User loadUserByUsername(String s) {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) user = new User();
        return user;
    }

    public int login(User user) {
        if(user.getUsername().length()==0 || user.getPassword().length()==0) return 1;
        User userDB = loadUserByUsername(user.getUsername());
        if(userDB.getUsername()==null || userDB.getUsername().length()==0 ) return 2;
        if (passwordEncoder.matches(user.getPassword(), userDB.getPassword())) return 0;
        else return 3;
    }

    public int reg(User user) {
        if(user.getUsername().length()==0 || user.getPassword().length()==0) return 1;
        User userDB = userMapper.loadUserByUsername(user.getUsername());
        if (userDB != null) return 2;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegTime(new Timestamp(System.currentTimeMillis()));
        long result = userMapper.reg(user);
        if (result == 1) return 0;
        else return 3;
    }

    public int deleteUserById(Long uid) {
        return userMapper.deleteUserById(uid);
    }

    public int updateUserRole(Long role, Long id) {
        return userMapper.setUserRole(role, id);
    }

    public List<User> getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public void regStatisticsPerDay() {
        userMapper.regStatisticsPerDay();
    }

    public void updateStatistics() {
        userMapper.updateStatistics();
    }

    public List<String> getDate() {
        return userMapper.getDate();
    }

    public List<Integer> getDataStatistics() {
        return userMapper.getDataStatistics();
    }
}