package com.mybatis.mysql.controller;

import com.mybatis.mysql.response.APIResponseModel;
import com.mybatis.mysql.model.User;
import com.mybatis.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // GET All Users
    @GetMapping("/list")
    public APIResponseModel<?> getAllUsers() {
        APIResponseModel<List<User>> response = new APIResponseModel<>();
        List<User> users = userRepository.findAll();
        if (users != null) {
            response.SetJSONResponseWithData(users, "users found !", HttpStatus.OK);
        } else {
            response.SetJSONResponse("server error !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // Create User
    @PostMapping("")
    public APIResponseModel<?> createUser(@RequestBody User user) {
        APIResponseModel<List<User>> response = new APIResponseModel<>();
        int insert = userRepository.insert(user);
        if (insert != 0) {
            response.SetJSONResponse("User created successfully", HttpStatus.CREATED);
        } else {
            response.SetJSONResponse("User created failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // Get User by ID
    @GetMapping("")
    public APIResponseModel<?> findUserById(@RequestParam Integer id) {
        APIResponseModel<User> response = new APIResponseModel<>();
        User user = userRepository.findById(id);
        if (user != null) {
            response.SetJSONResponseWithData(user, "user found !", HttpStatus.OK);
        } else {
            response.SetJSONResponse("user with id : " + id + " not found !", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    // Update user by ID
    @PutMapping("")
    public APIResponseModel<?> updateUser(@RequestBody User userDetails) {
        APIResponseModel<User> response = new APIResponseModel<>();
        User user = userRepository.findById(userDetails.getId());
        if (user == null) {
            response.SetJSONResponse("user not found !", HttpStatus.NOT_FOUND);
            return response;
        }
        int update = userRepository.update(userDetails);
        if (update != 0) {
            response.SetJSONResponseWithData(user, "user updated successfully", HttpStatus.OK);
        } else {
            response.SetJSONResponseWithData(user, "user updated failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // Delete User
    @DeleteMapping("/{id}")
    public APIResponseModel<?> deleteUser(@PathVariable Integer id) {
        APIResponseModel<User> response = new APIResponseModel<>();
        User user = userRepository.findById(id);
        if (user == null) {
            response.SetJSONResponse("user not found !", HttpStatus.NOT_FOUND);
            return response;
        }
        int delete = userRepository.deleteById(id);
        if (delete != 0) {
            response.SetJSONResponseWithData(user, "user successfully deleted !", HttpStatus.OK);
        } else {
            response.SetJSONResponseWithData(user, "user failed deleted !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}