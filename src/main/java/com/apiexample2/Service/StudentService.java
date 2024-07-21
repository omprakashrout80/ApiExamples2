package com.apiexample2.Service;

import com.apiexample2.Payload.NewDto;
import com.apiexample2.Payload.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    void deleteStudent(long id);

    StudentDto updateStudentById(long id, StudentDto studentDto);


    NewDto getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);
}