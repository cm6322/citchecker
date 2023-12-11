package com.group1.citchecker.Controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Service.ClassService;

@RestController
@RequestMapping("/class")
@CrossOrigin(origins ="*")

public class ClassController {

    @Autowired
    private ClassService classService;
   
    
    @Autowired
    public ClassController(ClassService classService ){
        this.classService = classService; 
        
    }

    // Insert class
    @PostMapping("/insertClass")
    public ClassEntity insertClass(@RequestBody ClassEntity classes) {
        return classService.insertClass(classes);
    }

    //Get all classes
    @GetMapping("/getAllClasses")
    public List<ClassEntity> getAllClasses() {
        return classService.getAllClasses();
    }

    // Get class by id
    @GetMapping("/classbyId/{cid}")
    public ClassEntity classById(@PathVariable int cid) {
        return classService.getClassById(cid);
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
    
    @PostMapping("/{classId}/addStudent/{studentId}")
    public ResponseEntity<String> addStudentToClass(
            @PathVariable int classId,
            @PathVariable int studentId
    ) {
        try {
            classService.addStudentToClass(classId, studentId);
            return new ResponseEntity<>("Student added to class successfully!", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Class or student not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding student to class: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to remove a student from a class
    @DeleteMapping("/{classId}/removeStudent/{studentId}")
    public ResponseEntity<String> removeStudentFromClass(
            @PathVariable int classId,
            @PathVariable int studentId
    ) {
        try {
            classService.removeStudentFromClass(classId, studentId);
            return new ResponseEntity<>("Student removed from class successfully!", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Class or student not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error removing student from class: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Set Teacher for Class using tid and cid
    @PostMapping("/setTeacher")
    public ResponseEntity<String> setTeacherforClass(@RequestBody Map<String, Integer> request) {
        int classId = request.get("classId");
        int teacherId = request.get("teacherId");

        return classService.setTeacherforClass(classId, teacherId);
    }
    
}