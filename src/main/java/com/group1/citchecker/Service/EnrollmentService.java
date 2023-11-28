package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.citchecker.Entity.EnrollmentEntity;
import com.group1.citchecker.Repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository erepo;

    // Create or insert an enrollment record
    public EnrollmentEntity insertEnrollment(EnrollmentEntity enrollment) {
        return erepo.save(enrollment);
    }

    // Get all enrollments
    public List<EnrollmentEntity> getAllEnrollments() {
        return erepo.findAll();
    }

    // Update an enrollment record
    public EnrollmentEntity updateEnrollment(int id, EnrollmentEntity newEnrollmentDetails) {
        EnrollmentEntity enrollment = erepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment " + id + " does not exist!"));

        // Update the record
        // Add additional fields to update as needed
        enrollment.setClassEntity(newEnrollmentDetails.getClassEntity());
        enrollment.setStudentEntity(newEnrollmentDetails.getStudentEntity());

        return erepo.save(enrollment);
    }

    // Delete an enrollment record
    public String deleteEnrollment(int id) {
        String msg = "";

        if (erepo.existsById(id)) {
            erepo.deleteById(id);
            msg = "Enrollment " + id + " is successfully deleted!";
        } else
            msg = "Enrollment " + id + " does not exist";
        return msg;
    }
}
