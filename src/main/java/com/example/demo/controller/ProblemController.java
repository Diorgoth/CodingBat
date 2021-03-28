package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Problem;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.ProblemDto;
import com.example.demo.service.ProblemService;
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
public class ProblemController {

    @Autowired
    ProblemService problemService;


    @PostMapping("/problem")
    public ResponseEntity<ApiResponce> addProblem(@Valid @RequestBody ProblemDto problemDto){

        ApiResponce apiResponce = problemService.addProblem(problemDto);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/problem/{id}")
    public ResponseEntity<ApiResponce> editProblem(@PathVariable Integer id, @Valid @RequestBody ProblemDto problemDto){

        ApiResponce apiResponce = problemService.editProblem(id, problemDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/problems")
    public ResponseEntity<List<Problem>> getProblem(){

        List<Problem> problems = problemService.getProblems();

        return ResponseEntity.ok(problems);

    }

    @GetMapping("/problem/{id}")
    public ResponseEntity<Problem> getProblem(@PathVariable Integer id){

        Problem problem = problemService.getProblem(id);

        return ResponseEntity.ok(problem);

    }

    @DeleteMapping("/problem")
    public ResponseEntity<ApiResponce> deleteProblem(@PathVariable Integer id){

        ApiResponce apiResponce = problemService.deleteProblem(id);

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
