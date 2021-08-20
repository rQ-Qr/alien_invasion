package edu.alieninvasion.backend.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
/**
 * <h1>MyPasswordEncoder</h1>
 * The configuration class to encode and decode the password
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    /**
     * This method is used to encode the password.
     *
     * @param rawPassword The original password that user inputs
     * @return String return the encoded password.
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    /**
     * This method is used to decode the password.
     *
     * @param rawPassword The original password that user inputs
     * @param encodedPassword The encoded password.
     * @return boolean check if passwords are equal.
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
    }
}