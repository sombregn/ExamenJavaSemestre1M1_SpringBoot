package org.sn.isi.dev.examenjava.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserProfileDto
    {
        @NotEmpty
        private String name;
        @NotEmpty
        private String prenom;
        @NotEmpty(message = "Email should not be empty")
        private String email;
        @NotEmpty(message = "Password should not be empty")
        private String password;

        @Override
        public String toString() {
            return "UserProfileDto{" +
                    "name='" + name + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
