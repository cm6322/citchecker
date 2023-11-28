package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.EnrollmentEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Service.ClassService;
import com.group1.citchecker.Service.TeacherService;

@RestController
@RequestMapping("/class")

public class ClassController {

    @Autowired
    private ClassService classService;
    private final TeacherService teacherService;
    
    @Autowired
    public ClassController(ClassService classService, TeacherService teacherService ){
        this.classService = classService;
        this.teacherService = teacherService;
        
    }

    @PostMapping("/insertClass")
    public ClassEntity insertClass(@RequestBody ClassEntity classes) {
        return classService.insertClass(classes);
    }

    @GetMapping("/getAllClasses")
    public List<ClassEntity> getAllClasses() {
        return classService.getAllClasses();
    }

    // Update class record
    @PutMapping("/updateClass/{cid}")
    public ClassEntity updateClass(@PathVariable int cid, @RequestBody ClassEntity newClassDetails) {
        return classService.updateClass(cid, newClassDetails);
    }

    // Delete class record
    @DeleteMapping("/deleteClass/{cid}")
    public String deleteClass(@PathVariable int cid) {
        return classService.deleteClass(cid);
    }
    
    @PostMapping("/{cid}/assignteacher/{tid}")
    public String assignTeacherToClass(@PathVariable int cid, @PathVariable int tid) {
        // Retrieve the class and teacher entities
        ClassEntity classEntity = classService.getClassById(cid);
        TeacherEntity teacherEntity = teacherService.getTeacherById(tid);

        // Check if both entities exist
        if (classEntity != null && teacherEntity != null) {
            // Assign the teacher to the class
            classEntity.setTeacher(teacherEntity);
            classService.saveClass(classEntity);

            return "Teacher assigned to class successfully.";
        } else {
            return "Class or teacher not found.";
        }
    }
    
    @PostMapping("/{cid}/enroll")
    public ResponseEntity<String> enrollStudent(@PathVariable int cid, @RequestBody EnrollmentEntity enrollment) {
        // Implement enrollment logic, associate with the class
        classService.enrollStudent(cid, enrollment);
        return new ResponseEntity<>("Student enrolled successfully.", HttpStatus.OK);
    }
}