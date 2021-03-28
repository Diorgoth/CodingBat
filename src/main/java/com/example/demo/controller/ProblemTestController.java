package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Problem;
import com.example.demo.entity.ProblemTest;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.ProblemTestDto;
import com.example.demo.service.ProblemTestService;
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
public class ProblemTestController {

    @Autowired
    ProblemTestService problemTestService;

    @PostMapping("/problemtest")
    public ResponseEntity<ApiResponce> addProblemTest(@Valid @RequestBody ProblemTestDto problemTestDto){

        ApiResponce apiResponce =  problemTestService.addProblemTest(problemTestDto);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/problemtest/{id}")
    public ResponseEntity<ApiResponce> editProblemTest(@PathVariable Integer id, @Valid @RequestBody ProblemTestDto problemTestDto){

        ApiResponce apiResponce =problemTestService.editProductTest(id, problemTestDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/problemtest")
    public ResponseEntity<List<ProblemTest>> getProblemTests(){

        List<ProblemTest> problemTests = problemTestService.getProblemTests();

        return ResponseEntity.ok(problemTests);

    }

    @GetMapping("/problemtest/{id}")
    public ResponseEntity<ProblemTest> getProblemTest(@PathVariable Integer id){

        ProblemTest problemTest = problemTestService.getProblemTest(id);

        return ResponseEntity.ok(problemTest);

    }

    @DeleteMapping("/problemtest/{id}")
    public ResponseEntity<ApiResponce> deleteProblemTest(@PathVariable Integer id){

        ApiResponce apiResponce = problemTestService.deleteProblemTest(id);


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
