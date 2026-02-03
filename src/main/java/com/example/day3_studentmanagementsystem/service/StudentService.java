package com.example.day3_studentmanagementsystem.service;

import com.example.day3_studentmanagementsystem.dto.StudentRequestDto;
import com.example.day3_studentmanagementsystem.dto.StudentResponseDto;
import com.example.day3_studentmanagementsystem.exception.StudentNotFoundException;
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
    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel(); //db ke liye
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        StudentModel saved = repository.save(student); // response bjhne ke liye client
        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()

        );
    }
    //Display Students

    public List<StudentResponseDto> getAllStudents(){
        return repository.findAll().stream().map(
                s   -> new StudentResponseDto(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                )).toList();
    }
    // update
    public StudentResponseDto updateStudent(String id, StudentRequestDto dto) {

        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        existingStudent.setName(dto.getName());
        existingStudent.setAge(dto.getAge());
        existingStudent.setEmail(dto.getEmail());

        StudentModel updatedStudent = repository.save(existingStudent);

        return new StudentResponseDto(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getAge(),
                updatedStudent.getEmail()
        );
    }


    public void deleteStudent(String id){
        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        repository.delete(student);
    }


}
