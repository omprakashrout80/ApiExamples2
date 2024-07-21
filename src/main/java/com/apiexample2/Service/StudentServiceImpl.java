package com.apiexample2.Service;

import com.apiexample2.Entity.Student;
import com.apiexample2.Payload.NewDto;
import com.apiexample2.Payload.StudentDto;
import com.apiexample2.Repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
        Student save = studentRepository.save(student);
        StudentDto dto = maptoDto(save);
        return dto;
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudentById(long id, StudentDto studentDto) {
        Optional<Student> byId = studentRepository.findById(id);
        Student student = byId.get();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        Student save = studentRepository.save(student);
        return maptoDto(save);
    }

    @Override
    public NewDto getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
        Page<Student> allStudents=studentRepository.findAll(pageable);
        List<StudentDto> studentDtos = allStudents.stream().map(as -> maptoDto(as)).collect(Collectors.toList());
        NewDto newDto=new NewDto();
        newDto.setAllDtos(studentDtos);
        newDto.setFirst(allStudents.isFirst());
        newDto.setLast(allStudents.isLast());
        newDto.setPageNo(pageable.getPageNumber());
        newDto.setPageSize(pageable.getPageSize());
        newDto.setTotalElements(allStudents.getTotalElements());
        newDto.setTotalPages(allStudents.getTotalPages());
        return newDto;
    }


    Student mapToEntity(StudentDto studentDto){
        Student student=new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        return student;
    }

    StudentDto maptoDto(Student stu){
        StudentDto dto=new StudentDto();
        dto.setId(stu.getId());
        dto.setName(stu.getName());
        dto.setEmail(stu.getEmail());
        dto.setPhone(stu.getPhone());
        return dto;
    }


}
