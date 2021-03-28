package com.example.demo.controller;

import com.example.demo.entity.Section;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.SectionDto;
import com.example.demo.service.SectionService;
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
public class SectionController {

    @Autowired
    SectionService sectionService;

    @PostMapping("/section")
    public ResponseEntity<ApiResponce> addSection(@Valid @RequestBody SectionDto sectionDto){

        ApiResponce apiResponce = sectionService.addSection(sectionDto);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }




    @PutMapping("/section/{id}")
    public ResponseEntity<ApiResponce> editSection(@PathVariable Integer id, @Valid @RequestBody  SectionDto sectionDto){

        ApiResponce apiResponce = sectionService.editSection(id, sectionDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/section")
    public ResponseEntity<List<Section>> getSections(){


        List<Section> sections = sectionService.getSections();

        return ResponseEntity.ok(sections);

    }

    @GetMapping("/section/{id}")
    public ResponseEntity<Section> getSection(@PathVariable Integer id){

        Section section = sectionService.getSection(id);

        return ResponseEntity.ok(section);

    }

    @DeleteMapping("/section/{id}")
    public ResponseEntity<ApiResponce> deleteSection(@PathVariable Integer id){

        ApiResponce apiResponce = sectionService.deleteSection(id);

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
