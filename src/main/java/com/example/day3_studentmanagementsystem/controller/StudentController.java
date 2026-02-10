package com.example.day3_studentmanagementsystem.controller;

import com.example.day3_studentmanagementsystem.dto.StudentRequestDto;
import com.example.day3_studentmanagementsystem.dto.StudentResponseDto;
import com.example.day3_studentmanagementsystem.service.StudentService;
import com.example.day3_studentmanagementsystem.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil JwtUtil;
    public StudentController(StudentService service, JwtUtil jwtUtil) {
        this.service = service;
        JwtUtil = jwtUtil;
    }
    private void CheckToken(String authHeader) {

        // 1️⃣ Header null ya empty
        if (authHeader == null || authHeader.isBlank()) {
            throw new RuntimeException("Authorization header missing");
        }

        // 2️⃣ Bearer token format check
        if (!authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization header format");
        }

        // 3️⃣ Token extract
        String token = authHeader.substring(7);

        JwtUtil.validateTokenAndGetEmail(token);


    }

    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@Valid @RequestBody StudentRequestDto student){
        return service.addStudent(student);
    }
    @GetMapping("/students")
    public List<StudentResponseDto> getStudents(){
        return service.getAllStudents();
    }
    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentRequestDto requestDto
    ) {
        return service.updateStudent(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
        return "Deleted successfully";
    }


}
