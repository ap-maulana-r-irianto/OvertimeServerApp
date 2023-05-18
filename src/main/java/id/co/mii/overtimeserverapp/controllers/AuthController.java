package id.co.mii.overtimeserverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.overtimeserverapp.models.User;
import id.co.mii.overtimeserverapp.models.dto.requests.LoginRequest;
import id.co.mii.overtimeserverapp.models.dto.requests.UserRequest;
import id.co.mii.overtimeserverapp.models.dto.responses.LoginResponse;
import id.co.mii.overtimeserverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    //method yang digunakan untuk menerima HTTP POST request pada endpoint "/login"
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    //method yang digunakan untuk menerima HTTP POST request pada endpoint "/register"
    @PostMapping("/register")
    public User register(@RequestBody UserRequest userRequest) {
        return authService.register(userRequest);
    }

    //method yang digunakan untuk menerima HTTP POST request pada endpoint "/create"
    @PostMapping("/create")
    public User create(@RequestBody UserRequest userRequest) {
        return authService.create(userRequest);
    }

}