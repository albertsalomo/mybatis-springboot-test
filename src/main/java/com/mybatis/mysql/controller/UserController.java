package com.mybatis.mysql.controller;

import com.mybatis.mysql.dto.GetUserByEmailId;
import com.mybatis.mysql.dto.GetUsersDetails;
import com.mybatis.mysql.entity.User;
import com.mybatis.mysql.response.APIResponseModel;
import com.mybatis.mysql.repository.UserRepository;
import com.mybatis.mysql.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Get Users by Id or non Id
    @GetMapping("")
    public APIResponseModel<?> getUserDetails(@RequestParam(required=false,defaultValue="") Integer id ) {
        if(id==null){
            APIResponseModel<List<GetUsersDetails>> response = new APIResponseModel<>();
            List<GetUsersDetails> users = userRepository.getUsersDetails();
            if (users != null) {
                response.SetJSONResponseWithData(users, "users found !", HttpStatus.OK);
            } else {
                response.SetJSONResponse("server error !", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return response;
        } else{
            APIResponseModel<GetUsersDetails> response = new APIResponseModel<>();
            GetUsersDetails user = userRepository.getUserDetailsById(id);
            if (user != null) {
                response.SetJSONResponseWithData(user, "user found !", HttpStatus.OK);
            } else {
                response.SetJSONResponse("user with id : " + id + " not found !", HttpStatus.NOT_FOUND);
            }
            return response;
        }
    }

    // Register User
    @PostMapping("")
    public APIResponseModel<?> insertUserData(@RequestBody User user) throws
            NoSuchAlgorithmException, InvalidKeySpecException {
        APIResponseModel<User> response = new APIResponseModel<>();
        PasswordGenerator pg = new PasswordGenerator();
        GetUserByEmailId checkEmail = userRepository.getTotalUserByEmailId(user.getEmailId());
        if(checkEmail.getTotal()!=0){
            response.SetJSONResponse("email id already existed !", HttpStatus.UNPROCESSABLE_ENTITY);
        } else{
            user.setPassword(pg.PBKDF2HashPassword(user.getPassword()));
            Integer insert = userRepository.insertUserData(user);
            if (insert!=0) {
                response.SetJSONResponse("User created successfully", HttpStatus.CREATED);
            } else {
                response.SetJSONResponse("User created failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    // Update user by ID
    @PutMapping("")
    public APIResponseModel<?> updateUserData(@RequestBody User userDetails) {
        APIResponseModel<GetUsersDetails> response = new APIResponseModel<>();
        GetUsersDetails user = userRepository.getUserDetailsById(userDetails.getId());
        if (user == null) {
            response.SetJSONResponse("user not found !", HttpStatus.NOT_FOUND);
            return response;
        }
        int update = userRepository.updateUserData(userDetails);
        if (update != 0) {
            response.SetJSONResponseWithData(user, "user updated successfully", HttpStatus.OK);
        } else {
            response.SetJSONResponseWithData(user, "user updated failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    // Delete User
    @DeleteMapping("")
    public APIResponseModel<?> deleteUser(@RequestParam Integer id) {
        APIResponseModel<GetUsersDetails> response = new APIResponseModel<>();
        GetUsersDetails user = userRepository.getUserDetailsById(id);
        if (user == null) {
            response.SetJSONResponse("user not found !", HttpStatus.NOT_FOUND);
            return response;
        }
        int delete = userRepository.deleteUserById(id);
        if (delete != 0) {
            response.SetJSONResponseWithData(user, "user successfully deleted !", HttpStatus.OK);
        } else {
            response.SetJSONResponseWithData(user, "user failed deleted !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}