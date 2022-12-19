package com.example.project.controller;


import com.example.project.model.Teacher;
import com.example.project.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping("/teachers")
    public ResponseEntity get(){
        ArrayList<Teacher> teachers = teacherService.getTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((errors.getFieldError().getDefaultMessage()));
        }
        teacherService.add(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(("added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((errors.getFieldError().getDefaultMessage()));
        }
        if(teacherService.isUpdated(id,teacher))
            return ResponseEntity.status(HttpStatus.OK).body(("Updated"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        if(teacherService.isDeleted(id))
            return ResponseEntity.status(HttpStatus.OK).body(("Deleted"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("not found"));
    }
    @GetMapping("/idteacher/{id}")
    public ResponseEntity idTeagher(@PathVariable Integer id){
        int index = teacherService.idHere(id);
        if(index == -1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("not found"));

        return ResponseEntity.status(HttpStatus.OK).body(teacherService.whoTeacher(index));
    }
    @GetMapping("/sal")
    public ResponseEntity salaryTeacher(@RequestBody Double salary){
        ArrayList<Teacher> salariesMoreThanOrEqual = teacherService.getSalaryTeachers(salary);
        return ResponseEntity.status(HttpStatus.OK).body(salariesMoreThanOrEqual);
    }
}
