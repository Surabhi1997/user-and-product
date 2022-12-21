package com.example.communication.service.impl;
import com.example.communication.dto.ProductDto;
import com.example.communication.dto.ResponseDto;
import com.example.communication.dto.UserDto;
import com.example.communication.entity.User;
import com.example.communication.exception.NoSuchProductExistsException;
import com.example.communication.repository.UserRepository;
import com.example.communication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {

        ResponseDto responseDto = new ResponseDto();

        try {
            User user = userRepository.findById(userId).get();
            UserDto userDto = mapToUser(user);

            ResponseEntity<ProductDto> responseEntity = restTemplate
                    .getForEntity("http://localhost:8081/products/" + user.getProductId(),
                            ProductDto.class);

            ProductDto productDto = responseEntity.getBody();

            System.out.println(responseEntity.getStatusCode());
            responseDto.setUser(userDto);
            responseDto.setProduct(productDto);

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new NoSuchProductExistsException("No product found with Productid saved for this user ");
        }
        return responseDto;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        list = userRepository.findAll();
        return list;
    }

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}