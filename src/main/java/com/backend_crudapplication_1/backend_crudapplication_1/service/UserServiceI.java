package com.backend_crudapplication_1.backend_crudapplication_1.service;

import com.backend_crudapplication_1.backend_crudapplication_1.UserEntity.UserEntity;

import java.util.List;

public interface UserServiceI {
    public UserEntity addUser(UserEntity user);
    public Long deleteUser(Long userId);
    public List<UserEntity> listOfAllUsers();
    public UserEntity searchUser(Long userId);
    public UserEntity updateUserEntity(UserEntity user);
}
