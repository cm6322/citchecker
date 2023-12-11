package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/teacherbyID/{tid}")
    public TeacherEntity getTeacherById(@PathVariable int tid) {
        return teacherService.getTeacherById(tid);
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
    /* 
    //Join Teacher to a class
    @PostMapping("/{tid}/joinClass")
    public ResponseEntity<String> addClassToTeacher(
            @PathVariable int tid,
            @RequestBody ClassEntity newClass
    ) {
        TeacherEntity teacher = teacherService.getTeacherById(tid);

        if (teacher != null) {
            // Optionally, you can set other properties of newClass here if needed
            teacherService.joinTeacherToClass(tid, newClass);
            return new ResponseEntity<>("Teacher joined successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Teacher not found.", HttpStatus.NOT_FOUND);
        }
    }*/

}