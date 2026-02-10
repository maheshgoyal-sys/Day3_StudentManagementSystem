package com.example.day3_studentmanagementsystem.controller;

import com.example.day3_studentmanagementsystem.dto.LoginRequestDto;
import com.example.day3_studentmanagementsystem.dto.RegisterRequestDto;
import com.example.day3_studentmanagementsystem.dto.TokenResponseDto;
import com.example.day3_studentmanagementsystem.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // ✅ REGISTER API
    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequestDto dto) {
        service.register(dto);
        return "User registered successfully";
    }

    // ✅ LOGIN API
    @PostMapping("/login")
    public TokenResponseDto login(
            @Valid @RequestBody LoginRequestDto dto) {
        return (TokenResponseDto) service.login(dto);
    }
}
