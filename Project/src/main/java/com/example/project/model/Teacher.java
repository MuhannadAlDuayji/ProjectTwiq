package com.example.project.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Teacher {
    @NotNull(message = "ID empty!!!")
    @Min(value = 1000,message = "ID must be more than 3 numbers!!!")
    private Integer id;
    @NotNull(message = "Name shouldn't be empty!!!")
    @Size(min = 3,message = "Name must be more than 2 character !!!")
    private String name;
    @NotNull(message = "Salary shouldn't be empty")
    private Double salary;


}