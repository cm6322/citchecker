package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.EnrollmentEntity;
import com.group1.citchecker.Entity.StudentEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Repository.ClassRepository;
import com.group1.citchecker.Repository.EnrollmentRepository;
import com.group1.citchecker.Repository.TeacherRepository;

@Service
public class ClassService {

    @Autowired
    private ClassRepository crepo;
    private EnrollmentRepository erepo;
    private TeacherRepository trepo;
    
    @Autowired
    public ClassService(ClassRepository crepo, EnrollmentRepository erepo, TeacherRepository trepo) {
        this.crepo = crepo;
        this.erepo = erepo;
        this.trepo = trepo;
    }

    // Create or insert a class record in tblclass
    public ClassEntity insertClass(ClassEntity classEntity) {
        return crepo.save(classEntity);
    }

    // Get all classes
    public List<ClassEntity> getAllClasses() {
        return crepo.findAll();
    }

    // Update a class record
    public ClassEntity updateClass(int sid, ClassEntity newClassDetails) {
        ClassEntity classEntity = crepo.findById(sid)
                .orElseThrow(() -> new NoSuchElementException("Class with ID " + sid + " does not exist"));

        // Update the record
        classEntity.setCode(newClassDetails.getCode());
        classEntity.setCourse(newClassDetails.getCourse());
        classEntity.setSchedule(newClassDetails.getSchedule());
        classEntity.setRoom(newClassDetails.getRoom());
        classEntity.setTime(newClassDetails.getTime());

        // Update other fields as needed

        return crepo.save(classEntity);
    }

    // Delete a class record
    public String deleteClass(int sid) {
        if (crepo.existsById(sid)) {
            crepo.deleteById(sid);
            return "Class " + sid + " is successfully deleted!";
        } else {
            return "Class " + sid + " does not exist";
        }
    }

    // Get a class by ID
    public ClassEntity getClassById(int cid) {
        return crepo.findById(cid).orElse(null);
    }

    // Save a class
    public void saveClass(ClassEntity classEntity) {
        crepo.save(classEntity);
    }

    // Get a teacher by ID
    public TeacherEntity getTeacherById(int tid) {
        return trepo.findById(tid).orElse(null);
    }

    // Save a teacher
    public void saveTeacher(TeacherEntity teacherEntity) {
        trepo.save(teacherEntity);
    }

    // Enroll a student in a class
    public void enrollStudent(int cid, EnrollmentEntity enrollment) {
        ClassEntity classEntity = crepo.findById(cid)
                .orElseThrow(() -> new NoSuchElementException("Class with ID " + cid + " not found"));

        if (enrollment != null) {
            // Add the enrollment to the class's list
            classEntity.addEnrollment(enrollment);

            // Save the class with the updated enrollment list
            crepo.save(classEntity);

            // Update the enrollment with the class information
            enrollment.setClassEntity(classEntity);
            erepo.save(enrollment);
        } else {
            // Handle the case where the enrollment is null
            System.out.println("Enrollment is null");
        }
    }
    
    public void addStudentToClass(int cid, StudentEntity student) {
        // Get the class by ID
        ClassEntity classEntity = crepo.findById(cid)
                .orElseThrow(() -> new NoSuchElementException("Class with ID " + cid + " not found"));

        // Ensure that both the class and student are not null
        if (classEntity != null && student != null) {
            // Create a new enrollment for the student in this class
            EnrollmentEntity enrollment = new EnrollmentEntity();
            enrollment.setStudentEntity(student);

            // Add the enrollment to the class's list of enrollments
            classEntity.addEnrollment(enrollment);

            // Save the updated class
            crepo.save(classEntity);
        } else {
            // Handle the case where either the class or student is null
            if (classEntity == null) {
                // Handle null class
                System.out.println("Class with ID " + cid+ " not found");
            }
            if (student == null) {
                // Handle null student
                System.out.println("Student is null");
            }
        }
    }
}
