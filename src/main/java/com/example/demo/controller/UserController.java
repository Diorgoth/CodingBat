package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponce;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user")
    public ResponseEntity<ApiResponce> addUser(@Valid @RequestBody User user){

        ApiResponce apiResponce = userService.addUser(user);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponce> editUser(@PathVariable Integer id, @Valid @RequestBody User user){

        ApiResponce apiResponce = userService.editUser(id, user);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser(){

        List<User> users = userService.getUsers();

        return ResponseEntity.ok(users);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){

        User user = userService.getUser(id);

        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponce> deleteUser(@PathVariable Integer id){

        ApiResponce apiResponce =  userService.deleteUser(id);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
