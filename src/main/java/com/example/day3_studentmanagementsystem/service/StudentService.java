package com.example.day3_studentmanagementsystem.service;

import com.example.day3_studentmanagementsystem.model.StudentModel;
import com.example.day3_studentmanagementsystem.repository.StudentRepo;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepo repository;

    public StudentService(StudentRepo repository) {
        this.repository = repository;
    }
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }

}
