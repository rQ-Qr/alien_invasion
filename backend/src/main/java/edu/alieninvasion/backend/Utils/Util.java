package edu.alieninvasion.backend.Utils;

import edu.alieninvasion.backend.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {
    public static User getCurrentUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (NullPointerException e) {
            return new User();
        }

        return user;
    }
}
