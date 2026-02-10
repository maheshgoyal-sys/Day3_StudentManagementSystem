package com.example.day3_studentmanagementsystem.repository;

import com.example.day3_studentmanagementsystem.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<UserModel,String> {
    Optional<UserModel> findByEmail(String email);
}
