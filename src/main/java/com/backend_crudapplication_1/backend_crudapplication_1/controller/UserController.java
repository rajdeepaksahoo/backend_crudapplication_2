package com.backend_crudapplication_1.backend_crudapplication_1.controller;

import com.backend_crudapplication_1.backend_crudapplication_1.UserEntity.UserEntity;
import com.backend_crudapplication_1.backend_crudapplication_1.usernotfound_exception.UserNotFoundException;
import com.backend_crudapplication_1.backend_crudapplication_1.service.UserServiceImp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserServiceImp service;
    @GetMapping("/viewall")
    public ResponseEntity<List<UserEntity>> listResponseEntity(){
        return ResponseEntity.ok(service.listOfAllUsers());
    }
    @PostMapping("/add")
    public ResponseEntity<Map<Long, HttpStatus>> add(@RequestBody UserEntity user){
        Map<Long, HttpStatus>map=new HashMap<>();
        map.put(user.getUserId(),HttpStatus.CREATED);
        service.addUser(user);
        return ResponseEntity.ok(map);
    }
    @JsonIgnore
    @PutMapping("/update")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user){
        service.updateUserEntity(user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity delete(@PathVariable Long userId){
        Long userIdret = service.deleteUser(userId);
        if(userIdret==null)
            return ResponseEntity.badRequest().body(userIdret);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<UserEntity> search(@PathVariable Long userId) throws UserNotFoundException {
        System.out.println(userId+" fbsejdcm");

        if(service.searchUser(userId)==null){
            throw new UserNotFoundException("User Is Not Available");
        }
        return ResponseEntity.ok(service.searchUser(userId));
    }
}
