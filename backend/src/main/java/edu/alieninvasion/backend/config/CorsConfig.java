package edu.alieninvasion.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <h1>CorsConfig</h1>
 * The configuration class to solve cross-origin resource sharing
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 set request origin address
        corsConfiguration.addAllowedHeader("*"); // 2 set request header
        corsConfiguration.addAllowedMethod("*"); // 3 set request method
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}