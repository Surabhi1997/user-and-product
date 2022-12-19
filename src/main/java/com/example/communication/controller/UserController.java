package com.example.communication.controller;

import com.example.communication.dto.ResponseDto;
import com.example.communication.entity.User;
import com.example.communication.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor

//@NoArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId){
        ResponseDto responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/list")
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }

    public String getToken() throws RestClientException, IOException
    {
        String baseUrl = "http://localhost:8089/gettoken";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try
        {
         response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(response.getBody());

        return response.getBody().toString();
    }

    private static HttpEntity<?> getHeaders() throws IOException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}