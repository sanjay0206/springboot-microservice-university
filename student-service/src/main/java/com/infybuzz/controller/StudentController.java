package com.infybuzz.controller;

import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.StudentResponse;
import com.infybuzz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping("getById/{id}")
    public StudentResponse getById(@PathVariable long id) {
        return studentService.getById(id);
    }
}