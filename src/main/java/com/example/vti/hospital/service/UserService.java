package com.example.vti.hospital.service;

import com.example.vti.hospital.models.User;
import com.example.vti.hospital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configuration
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUserByPaging(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> listBooking = userRepository.findAllByIsActive(true,pageable);
        return listBooking.stream().toList();
    }

    public List<User> getAllUser(){
        return userRepository.findAllUserByIsActive(true);
    }

    public List<User> insertUser(User newUser) {
        List<User> userList = new ArrayList<>();
        userList.add(userRepository.save(newUser));
        return userList;
    }

    public User deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        user.setActive(false);
        User returnUser = userRepository.save(user);
        return returnUser;
    }

    public Optional<User> updateUser(User newUser) {
        Optional<User> userOptional = userRepository.findById(newUser.getId());
        if(userOptional.isPresent()){
            User foundUser = userOptional.get();
            foundUser.setPassWord(newUser.getPassWord());
            foundUser.setActive(true);
            foundUser.setEmail(newUser.getEmail());
            userRepository.save(foundUser);
        }
        return userOptional;
    }
}
