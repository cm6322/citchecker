package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.EnrollmentEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/insertTeacher")
    public TeacherEntity insertTeacher(@RequestBody TeacherEntity teacher) {
        return teacherService.insertTeacher(teacher);
    }

    @GetMapping("/getAllTeachers")
    public List<TeacherEntity> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Update teacher record
    @PutMapping("/updateTeacher/{tid}")
    public TeacherEntity updateTeacher(@PathVariable int tid, @RequestBody TeacherEntity newTeacherDetails) {
        return teacherService.updateTeacher(tid, newTeacherDetails);
    }

    // Delete teacher record
    @DeleteMapping("/deleteTeacher/{tid}")
    public String deleteTeacher(@PathVariable int tid) {
        return teacherService.deleteTeacher(tid);
    }
    @PostMapping("/{tid}/addClass")
    public ResponseEntity<String> addClassToTeacher(
            @PathVariable int tid,
            @RequestBody ClassEntity newClass
    ) {
        TeacherEntity teacher = teacherService.getTeacherById(tid);

        if (teacher != null) {
            // Optionally, you can set other properties of newClass here if needed
            teacherService.addClassToTeacher(tid, newClass);
            return new ResponseEntity<>("Class added to teacher successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Teacher not found.", HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/{tid}/enroll")
    public ResponseEntity<String> enrollStudent(@PathVariable int tid, @RequestBody EnrollmentEntity enrollment) {
        // Implement enrollment logic, associate with the teacher
        teacherService.enrollStudent(tid, enrollment);
        return new ResponseEntity<>("Student enrolled successfully.", HttpStatus.OK);
    }

}