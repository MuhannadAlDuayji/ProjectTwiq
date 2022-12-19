package com.example.project.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotNull(message = "ID empty!!!")
    @Min(value = 1000,message = "ID must be more than 3 numbers!!!")
    private Integer id;
    @NotNull(message = "Name shouldn't be empty!!!")
    @Size(min = 3,message = "Name must be more than 2 character !!!")
    private String name;
    @NotNull(message = "Age shouldn't be null!!!")
    private Integer age;
    @NotNull(message = "Major shouldn't be empty!!!")
    @Pattern(regexp = "(CS|MATH|Engineer)",message = "Major just CS, MATH, Engineer")
    private String major;
}