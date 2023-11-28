package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.group1.citchecker.Entity.StudentEntity;
import com.group1.citchecker.Service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins ="*")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/insertStudent")
    public StudentEntity insertStudent(@RequestBody StudentEntity student) {
        return studentService.insertStudent(student);
    }

    @GetMapping("/getAllStudents")
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Update student record
    @PutMapping("/updateStudent/{sid}")
    public StudentEntity updateStudent(@PathVariable int sid, @RequestBody StudentEntity newStudentDetails) {
        return studentService.updateStudent(sid, newStudentDetails);
    }

    // Delete student record
    @DeleteMapping("/deleteStudent/{sid}")
    public String deleteStudent(@PathVariable int sid) {
        return studentService.deleteStudent(sid);
    }

}