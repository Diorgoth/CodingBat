package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.payload.ApiResponce;
import com.example.demo.service.CourseService;
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
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<ApiResponce> addCourse(@Valid @RequestBody Course course){

        ApiResponce apiResponce = courseService.addCourse(course);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/course/{id}")
    public ResponseEntity<ApiResponce> editCourse(@PathVariable Integer id, @Valid @RequestBody Course course){

        ApiResponce apiResponce = courseService.editCourse(id, course);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(){

        List<Course> courses = courseService.getCourses();

        return ResponseEntity.ok(courses);

    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Integer id){

        Course course = courseService.getCourse(id);

        return ResponseEntity.ok(course);

    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<ApiResponce> deleteCourse(@PathVariable Integer id){

        ApiResponce apiResponce = courseService.deleteCourse(id);

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
