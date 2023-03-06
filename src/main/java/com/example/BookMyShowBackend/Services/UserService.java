package com.example.BookMyShowBackend.Services;

import com.example.BookMyShowBackend.Dtos.UserRequestDto;
import com.example.BookMyShowBackend.Models.UserEntity;
import com.example.BookMyShowBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String createUser(UserRequestDto userRequestDto){
        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();
        try{
            userRepository.save(userEntity);
        }catch (Exception e){
            return "User couldn't be added";
        }
        return "User added successfully";

    }
}
