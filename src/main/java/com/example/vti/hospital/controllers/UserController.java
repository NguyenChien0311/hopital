package com.example.vti.hospital.controllers;

import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.models.User;
import com.example.vti.hospital.repositories.UserRepository;
import com.example.vti.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("")
//    Test postman:      http://localhost:8080/api/v1/users?pageNumber=0&pageSize=999
    ResponseEntity<ResponseObject> listUser(@RequestParam int pageNumber, @RequestParam int pageSize){
        return  ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"Success", userService.getUserByPaging(pageNumber,pageSize)));
    }
//    @GetMapping("")
//    ResponseEntity<ResponseObject> getAll(){
//        return  ResponseEntity.status(HttpStatus.OK).
//                body(new ResponseObject(200,"success", userService.getAllUser()));
//    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {

        Optional<User> foundUser = userRepository.findByUserName(newUser.getUserName());

        if (foundUser.isPresent())
        {
            return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
                    body(new ResponseObject(200,"User already exist", "" ));
        }
        return  ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"success", userService.insertUser(newUser) ));
    }
    @GetMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteUserById(@PathVariable Long id)
    {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            return  ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseObject(200,"success", userService.deleteUser(id)));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ResponseObject(200,"Supporter id not found", "" ));
    }

    @PostMapping("/update")
    ResponseEntity<ResponseObject> updateUserById(@RequestBody User newUser)
    {
        return  ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"success", userService.updateUser(newUser)));
    }
}
//    @PostMapping("/update")
//        //    http://localhost:8080/api/v1/rate/update
//    ResponseEntity<ResponseObject> update(@RequestBody Rate newRate)
//    {
//        return  ResponseEntity.status(HttpStatus.OK).
//                body(new ResponseObject(200,200, rateService.update(newRate)));
//    }
