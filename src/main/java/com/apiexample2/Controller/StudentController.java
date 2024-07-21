package com.apiexample2.Controller;


import com.apiexample2.Payload.NewDto;
import com.apiexample2.Payload.StudentDto;
import com.apiexample2.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/studentreg")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }



    @PostMapping
    public  ResponseEntity<?> addStudent(
            @Valid @RequestBody StudentDto studentDto,
            BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
        StudentDto dto=studentService.addStudent(studentDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStu(@RequestParam long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Record Deleted",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStu(@RequestParam long id,@RequestBody StudentDto studentDto){
        StudentDto dto=studentService.updateStudentById(id,studentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/v2/studentreg?pageNo=0&pageSize=3&sortBy=id&sortDir=asc

    @GetMapping
    public  ResponseEntity<NewDto> getStudent(
            @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = "asc",required = false) String sortDir

    ){
        NewDto dtos= studentService.getAllStudents(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }


}
