package com.example.demo.Data;

import com.example.demo.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
