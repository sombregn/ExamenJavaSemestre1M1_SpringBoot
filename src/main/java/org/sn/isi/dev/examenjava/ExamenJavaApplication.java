package org.sn.isi.dev.examenjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExamenJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenJavaApplication.class, args);
    }
}
