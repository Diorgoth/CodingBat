package com.example.demo.controller;

import com.example.demo.entity.ProblemTest;
import com.example.demo.entity.UserPractice;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.ProblemTestDto;
import com.example.demo.payload.UserPracticeDto;
import com.example.demo.service.UserPracticeService;
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
public class UserPracticeController {

    @Autowired
    UserPracticeService userPracticeService;


    @PostMapping("/userpractice")
    public ResponseEntity<ApiResponce> addUserPractice(@Valid @RequestBody UserPracticeDto userPracticeDto){

        ApiResponce apiResponce = userPracticeService.addUserPractice(userPracticeDto);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/userpractice/{id}")
    public ResponseEntity<ApiResponce> editUserPractice(@PathVariable Integer id, @Valid @RequestBody UserPracticeDto userPracticeDto){

        ApiResponce apiResponce =userPracticeService.editUserPractice(id, userPracticeDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/userpractices")
    public ResponseEntity<List<UserPractice>> getUserPractice(){

        List<UserPractice> userPractices = userPracticeService.getUserPractices();

        return ResponseEntity.ok(userPractices);

    }

    @GetMapping("/userpractice/{id}")
    public ResponseEntity<UserPractice> getUserPractice(@PathVariable Integer id){

        UserPractice userPractice = userPracticeService.getUserPractice(id);

        return ResponseEntity.ok(userPractice);

    }

    @DeleteMapping("/userpractice/{id}")
    public ResponseEntity<ApiResponce> deleteProblemTest(@PathVariable Integer id){

        ApiResponce apiResponce = userPracticeService.deleteUserPractice(id);


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
