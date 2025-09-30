package com.adso.aprendiz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);
    private static final String DEFAULT_ALLOWED_ORIGINS = "*"; // cambiar a dominio específico en prod

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String originsEnv = System.getProperty("CORS_ALLOWED_ORIGINS", System.getenv("CORS_ALLOWED_ORIGINS"));
        if (originsEnv == null || originsEnv.isBlank()) {
            originsEnv = DEFAULT_ALLOWED_ORIGINS;
        }
        List<String> origins = Arrays.stream(originsEnv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        boolean allowCreds = parseBoolean(System.getProperty("CORS_ALLOW_CREDENTIALS", System.getenv("CORS_ALLOW_CREDENTIALS")), false);

        log.info("Config CORS -> origins: {}, allowCredentials: {}", origins, allowCreds);

        var mapping = registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(allowCreds)
                .maxAge(3600);

        boolean hasWildcard = origins.stream().anyMatch(o -> o.contains("*"));
        if (hasWildcard) {
            mapping.allowedOriginPatterns(origins.toArray(new String[0]));
        } else {
            mapping.allowedOrigins(origins.toArray(new String[0]));
        }
    }

    private boolean parseBoolean(String value, boolean defaultVal) {
        if (value == null) return defaultVal;
        String v = value.trim().toLowerCase();
        return v.equals("true") || v.equals("1") || v.equals("yes");
    }
}
