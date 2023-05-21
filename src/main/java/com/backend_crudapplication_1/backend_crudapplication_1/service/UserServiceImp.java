package com.backend_crudapplication_1.backend_crudapplication_1.service;

import com.backend_crudapplication_1.backend_crudapplication_1.UserEntity.UserEntity;
import com.backend_crudapplication_1.backend_crudapplication_1.repository.RepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserServiceI{
    @Autowired
    RepositoryI repo;
    @Override
    public UserEntity addUser(UserEntity user) {
        return repo.save(user);
    }

    @Override
    public Long deleteUser(Long userId) {
        Optional<UserEntity> optional= repo.findById(userId);
        if (optional.get()==null) return null;
        return userId;
    }

    @Override
    public List<UserEntity> listOfAllUsers() {
        return repo.findAll();
    }

    @Override
    public UserEntity searchUser(Long userId) {
        Optional<UserEntity> user= repo.findById(userId);
        return user.get();
    }

    @Override
    public UserEntity updateUserEntity(UserEntity user) {
        return repo.save(user);
    }
}
