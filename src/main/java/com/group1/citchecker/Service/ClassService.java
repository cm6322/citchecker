package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.StudentEntity;
import com.group1.citchecker.Repository.ClassRepository;
import com.group1.citchecker.Repository.StudentRepository;


@Service
public class ClassService {

    @Autowired
    private ClassRepository crepo;
    
    @Autowired
    private StudentRepository srepo;

    
    @Autowired
    public ClassService(ClassRepository crepo,StudentRepository srepo) {
        this.crepo = crepo;
        this.srepo = srepo;
       
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
    public void addStudentsToClass(List<Integer> sid, int cid) {
        Optional<ClassEntity> optionalClass = crepo.findById(cid);

        if (optionalClass.isPresent()) {
            ClassEntity schoolClass = optionalClass.get();
            
            List<StudentEntity> students = srepo.findAllById(sid);
            schoolClass.getStudents().addAll(students);

            crepo.save(schoolClass);
        } else {
            // Handle class not found
            throw new EntityNotFoundException("Class with ID " + cid + " not found.");
        }
    }
}