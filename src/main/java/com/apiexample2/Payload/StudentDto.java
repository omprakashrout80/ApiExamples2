package com.apiexample2.Payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentDto {

    private Long id;

    @Size(min = 2, message = "Name should have more than 2 characters")
    private String name;

    @Email(message="Enter a valid email type !!")
    private String email;


    @Size(min = 10, max = 10,message = "Phone should have 10 numbers !!")
    private String phone;

}