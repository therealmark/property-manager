package com.example.propertymanager.service;

import com.example.propertymanager.client.DBClient;
import com.example.propertymanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 *
 **/
@Service
@DependsOn(value = "DBClient")
public class UserService {

    private DBClient dbClient;

    @Autowired
    public UserService(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    public User createUser(User user) {
        return dbClient.insertUser(user);
    }

    public User findUserById(String id) {
        return dbClient.findUserById(id);
    }
}
