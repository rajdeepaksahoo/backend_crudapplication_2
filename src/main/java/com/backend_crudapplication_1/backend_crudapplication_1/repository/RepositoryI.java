package com.backend_crudapplication_1.backend_crudapplication_1.repository;

import com.backend_crudapplication_1.backend_crudapplication_1.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryI extends JpaRepository<UserEntity,Long> {
}
