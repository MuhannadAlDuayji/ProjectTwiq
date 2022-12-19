package com.example.project.controller;


import com.example.project.model.Student;
import com.example.project.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/students")
    public ResponseEntity get(){
        ArrayList<Student> students = studentService.getStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid  Student student, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((errors.getFieldError().getDefaultMessage()));
        studentService.add(student);
        return ResponseEntity.status(HttpStatus.OK).body(("added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((errors.getFieldError().getDefaultMessage()));
        }
        if(studentService.isUpdated(id,student))
            return ResponseEntity.status(HttpStatus.OK).body(("Updated"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        if(studentService.isDeleted(id))
            return ResponseEntity.status(HttpStatus.OK).body(("Deleted"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("not found"));
    }

    @GetMapping("/idstudent/{id}")
    public ResponseEntity idStudent(@PathVariable Integer id){
        int index = studentService.idHere(id);
        if(index == -1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("not found"));

        return ResponseEntity.status(HttpStatus.OK).body(studentService.whoStudent(index));
    }
    @GetMapping("/namestudent")
    public ResponseEntity nameStudent(@RequestBody String name){
        int index = studentService.nameHere(name);
        if(index == -1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("not found"));

        return ResponseEntity.status(HttpStatus.OK).body(studentService.whoStudent(index));
    }

    @GetMapping("/major")
    public ResponseEntity majorStudents(@RequestBody String major){
        ArrayList<Student> majors = studentService.getMajorStudents(major);
        return ResponseEntity.status(HttpStatus.OK).body(majors);
    }
    @GetMapping("/ages")
    public ResponseEntity ageStudents(@RequestBody Integer age){
        ArrayList<Student> ages = studentService.getAgeStudents(age);
        return ResponseEntity.status(HttpStatus.OK).body(ages);
    }
}