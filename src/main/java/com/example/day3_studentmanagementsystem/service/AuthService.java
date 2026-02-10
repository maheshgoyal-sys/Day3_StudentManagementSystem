package com.example.day3_studentmanagementsystem.service;

import com.example.day3_studentmanagementsystem.dto.LoginRequestDto;
import com.example.day3_studentmanagementsystem.dto.RegisterRequestDto;
import com.example.day3_studentmanagementsystem.dto.TokenResponseDto;
import com.example.day3_studentmanagementsystem.model.UserModel;
import com.example.day3_studentmanagementsystem.repository.UserRepo;
import com.example.day3_studentmanagementsystem.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo repository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepo repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    // ✅ LOGIN
    public TokenResponseDto login(LoginRequestDto dto) {

        UserModel user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new TokenResponseDto(token);
    }

    // ✅ REGISTER
    public TokenResponseDto register(RegisterRequestDto dto) {

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        UserModel newUser = new UserModel();
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword()); // later encrypt

        repository.save(newUser);

        String token = jwtUtil.generateToken(newUser.getEmail());
        return new TokenResponseDto(token);
    }
}
