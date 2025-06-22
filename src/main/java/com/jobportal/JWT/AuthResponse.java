package com.jobportal.JWT;
import lombok.Data;


@Data
public class AuthResponse {
    private final String Jwt;
    public AuthResponse(String jwt) {
        this.Jwt = jwt;
    }

}
