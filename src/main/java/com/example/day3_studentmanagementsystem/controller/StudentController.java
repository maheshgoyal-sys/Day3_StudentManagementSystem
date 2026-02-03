package com.example.day3_studentmanagementsystem.controller;

import com.example.day3_studentmanagementsystem.dto.StudentRequestDto;
import com.example.day3_studentmanagementsystem.dto.StudentResponseDto;
import com.example.day3_studentmanagementsystem.model.StudentModel;
import com.example.day3_studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
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
