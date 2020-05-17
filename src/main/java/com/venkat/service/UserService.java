package com.venkat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venkat.model.User;
import com.venkat.repositry.UserRepositry;

@Service
public class UserService {

    @Autowired
    private UserRepositry repositry ;

    public User save(User user){
       return repositry.save(user);
    }

    public Optional<User> getUSerById(int id){
        return repositry.findById(id);
    }

    public List<User> GetAllUser() {
        return this.repositry.findAll();
    }

    public void DeleteUser(int id ){
        repositry.deleteById(id);
    }
}
