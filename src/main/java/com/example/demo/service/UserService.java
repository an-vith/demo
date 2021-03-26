package com.example.demo.service;

import com.example.demo.Data.UserRepository;
import com.example.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String registerUser(String username, String password, String email){
        try {
            Optional<UserEntity> userEntity = userRepository.findById(username);
            if (userEntity.isPresent()) {
                return "User with UserName already Exists. Try with different Username.";
            } else {
                userRepository.save(new UserEntity(username, password, email));
                return "success";
            }
        }catch(Exception ex){
            return "Exception Occurred. Please Retry.";
        }
    }

    public String login(String username, String password){
        try {
            Optional<UserEntity> userEntity = userRepository.findById(username);
            if (userEntity.isPresent()) {
                if(userEntity.get().getPassword().equals(password)) {
                    return "success";
                }else return "No User Found with given credentials. Please try with valid Credentials.";
            } else {
                return "Invalid Credentials";
            }
        }catch(Exception ex){
            return "Exception Occurred. Please Retry.";
        }
    }
}
