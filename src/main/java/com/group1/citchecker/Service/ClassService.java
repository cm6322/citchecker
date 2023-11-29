package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Repository.ClassRepository;
import com.group1.citchecker.Repository.TeacherRepository;

@Service
public class ClassService {

    @Autowired
    private ClassRepository crepo;
    
    @Autowired
    private TeacherRepository trepo;
    
    @Autowired
    public ClassService(ClassRepository crepo,TeacherRepository trepo) {
        this.crepo = crepo;
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

}