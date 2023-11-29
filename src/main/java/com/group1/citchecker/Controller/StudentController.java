package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group1.citchecker.Entity.ClassEntity;
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
    @PostMapping("/{sid}/assignClass")
    public ResponseEntity<String> addClassToTeacher(
            @PathVariable int sid,
            @RequestBody ClassEntity newClass
    ) {
        StudentEntity student = studentService.getStudentById(sid);

        if (student != null) {
            // Optionally, you can set other properties of newClass here if needed
        	studentService.addClassToStudent(sid, newClass);
            return new ResponseEntity<>("Student added successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Class not found.", HttpStatus.NOT_FOUND);
        }
    }

}