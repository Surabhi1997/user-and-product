package com.example.communication.service;


import com.example.communication.dto.ResponseDto;
import com.example.communication.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);

    List<User> getAllUsers();
}