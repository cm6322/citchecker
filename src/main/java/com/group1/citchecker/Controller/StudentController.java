package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.group1.citchecker.Entity.StudentEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Service.StudentService;
import com.group1.citchecker.Service.TeacherService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private TeacherService teacherService;


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
    
    @PutMapping("/associateTeacher/{sid}/{tid}")
    public StudentEntity associateTeacher(
            @PathVariable int sid,
            @PathVariable int tid
    ) {
        StudentEntity student = studentService.getStudentById(sid);
        TeacherEntity teacher = teacherService.getTeacherById(tid);

        if (student != null && teacher != null) {
            student.setTeacher(teacher);
            return studentService.insertStudent(student);
        } else {
            // Handle error, e.g., teacher or student not found
            return null;
        }
    }
}