package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.StudentEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Repository.ClassRepository;
import com.group1.citchecker.Repository.StudentRepository;
import com.group1.citchecker.Repository.TeacherRepository;


@Service
public class ClassService {

    @Autowired
    private ClassRepository crepo;

    @Autowired
    private TeacherRepository trepo;

    @Autowired
    private StudentRepository srepo;

    
    @Autowired
    public ClassService(ClassRepository crepo, StudentRepository srepo, TeacherRepository trepo) {
        this.crepo = crepo;
        this.srepo = srepo;
        this.trepo = trepo;
    }

    //Set a Teacher for Class
    public ResponseEntity<String> setTeacherforClass(int cid, int tid) {
        Optional<ClassEntity> cOptional = crepo.findById(cid);
        Optional<TeacherEntity> tOptional = trepo.findById(tid);

        if (cOptional.isPresent() && tOptional.isPresent()) {
            ClassEntity classEntity = cOptional.get();
            TeacherEntity teacherEntity = tOptional.get();

            classEntity.setTeacher(teacherEntity);
            crepo.save(classEntity);

            return new ResponseEntity<>("Teacher assigned to class successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Class or Teacher not found.", HttpStatus.NOT_FOUND);
        }

    }

    // Create or insert a class record in tblclass
    public ClassEntity insertClass(ClassEntity classEntity) {
        return crepo.save(classEntity);
    }

    // Get all classes
    public List<ClassEntity> getAllClasses() {
        return crepo.findAll();
    }

    public ClassEntity getClassById(int cid) {
        return crepo.findById(cid).orElse(null);
    }

    // Update a class record
    public ClassEntity updateClass(int cid, ClassEntity newClassDetails) {
        ClassEntity classEntity = crepo.findById(cid)
                .orElseThrow(() -> new NoSuchElementException("Class with ID " + cid + " does not exist"));

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
    public String deleteClass(int cid) {
        if (crepo.existsById(cid)) {
            crepo.deleteById(cid);
            return "Class " + cid + " is successfully deleted!";
        } else {
            return "Class " + cid + " does not exist";
        }
    }
    public void addStudentToClass(int classId, int studentId) {
        ClassEntity classEntity = crepo.findById(classId)
                .orElseThrow(() -> new NoSuchElementException("Class not found"));

        StudentEntity studentEntity = srepo.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));

        // Add the student to the class and save the changes
        classEntity.getStudents().add(studentEntity);
        crepo.save(classEntity);
    }

    public void removeStudentFromClass(int classId, int studentId) {
        ClassEntity classEntity = crepo.findById(classId)
                .orElseThrow(() -> new NoSuchElementException("Class not found"));

        StudentEntity studentEntity = srepo.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));

        // Remove the student from the class and save the changes
        classEntity.getStudents().remove(studentEntity);
        crepo.save(classEntity);
    }

}