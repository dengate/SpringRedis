package com.example.account.service;


import com.example.account.dto.request.UserRequest;
import com.example.account.dto.response.UserResponse;
import com.example.account.model.User;
import com.example.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserResponse add(User user){
            return userModelToUserResponseDto(repository.save(user));
    }

    @Cacheable(value = "userCache")
    public List<UserResponse> get(){
        return userModelListToUserResponseList(repository.findAll());
    }

    public UserResponse userModelToUserResponseDto(User user){
        UserResponse response = new UserResponse();
        response.username = user.getUsername();
        response.storeName = response.username + " Store";
        return response;
    }

    public List<UserResponse> userModelListToUserResponseList(List<User> users){
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user:users){
            userResponseList.add(userModelToUserResponseDto(user));
        }

        return userResponseList;
    }

}
