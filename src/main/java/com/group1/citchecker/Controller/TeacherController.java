package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.group1.citchecker.Entity.ClassEntity;
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
    @PostMapping("/addClass/{tid}")
    public TeacherEntity addClass(@PathVariable int tid, @RequestBody ClassEntity newClass) {
        return teacherService.addClass(tid, newClass);
    }

}