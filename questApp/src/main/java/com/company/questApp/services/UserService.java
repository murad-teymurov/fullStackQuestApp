package com.company.questApp.services;

import com.company.questApp.entites.User;
import com.company.questApp.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    public User addUser(User newUser){
        return  userRepository.save(newUser);
    }

    public User getById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }
    public User updateUser(User newUser, Long userId){
        Optional<User> opUser = userRepository.findById(userId);
        if(opUser.isPresent()){
            User foundUser = opUser.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        return null;
    }
    public void removeById(Long userId){
        userRepository.deleteById(userId);
    }


}
