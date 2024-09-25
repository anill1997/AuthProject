package com.auth.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CredentialsDto {
    private String login;
    private char[] password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "CredentialsDto{" +
                "login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
