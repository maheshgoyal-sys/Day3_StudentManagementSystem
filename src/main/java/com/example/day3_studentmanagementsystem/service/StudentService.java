package com.example.day3_studentmanagementsystem.service;

import com.example.day3_studentmanagementsystem.model.StudentModel;
import com.example.day3_studentmanagementsystem.repository.StudentRepo;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StudentService {
    private final StudentRepo repository;

    public StudentService(StudentRepo repository) {
        this.repository = repository;
    }
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }
    //Display Students

    public List<StudentModel> getStudents(){
        return repository.findAll();
    }
    // update
    public StudentModel updateStudent(String id, StudentModel student){
        StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new RuntimeException("No Student Found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
        return repository.save(existingStudent);



    }

    public void deleteStudent(String id){
        repository.deleteById(id);
    }

}
