package com.group1.citchecker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group1.citchecker.Entity.EnrollmentEntity;
import com.group1.citchecker.Service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/insertEnrollment")
    public EnrollmentEntity insertEnrollment(@RequestBody EnrollmentEntity enrollment) {
        return enrollmentService.insertEnrollment(enrollment);
    }

    @GetMapping("/getAllEnrollments")
    public List<EnrollmentEntity> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @PutMapping("/updateEnrollment/{id}")
    public EnrollmentEntity updateEnrollment(
            @PathVariable int id, @RequestBody EnrollmentEntity newEnrollmentDetails) {
        return enrollmentService.updateEnrollment(id, newEnrollmentDetails);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable int id) {
        String result = enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok(result);
    }
}
