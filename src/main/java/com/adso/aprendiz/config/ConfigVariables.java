package com.adso.aprendiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Configuration
public class ConfigVariables {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        loadDotEnv();
    }

    private void loadDotEnv() {
        try {
            Path envPath = Paths.get(".env");
            if (Files.exists(envPath)) {
                Properties props = new Properties();
                props.load(new FileInputStream(".env"));

                props.forEach((key, value) -> {
                    String keyStr = key.toString();
                    String valueStr = value.toString();

                    // Solo establecer si la variable de entorno no existe ya
                    if (System.getenv(keyStr) == null && System.getProperty(keyStr) == null) {
                        System.setProperty(keyStr, valueStr);
                    }
                });

                System.out.println("Archivo .env cargado correctamente");
            } else {
                System.out.println(" Archivo .env no encontrado, usando variables de entorno del sistema");
            }
        } catch (IOException e) {
            System.err.println(" Error al cargar el archivo .env: " + e.getMessage());
        }
    }
}
