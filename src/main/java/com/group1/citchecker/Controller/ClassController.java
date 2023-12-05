package com.group1.citchecker.Controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
    @PostMapping("/{cid}/addStudents")
    public ResponseEntity<String> addStudentsToClass(
            @PathVariable int cid,
            @RequestBody List<Integer> sid) {

        try {
            classService.addStudentsToClass(sid, cid);
            return ResponseEntity.ok("Students added to class successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Class not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding students to class");
        }
    }
    
    
    
}