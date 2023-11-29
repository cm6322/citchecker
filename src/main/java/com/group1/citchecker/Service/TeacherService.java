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
public class TeacherService {

	@Autowired
	private ClassRepository crepo;
	    
	@Autowired
	private TeacherRepository trepo;
	    
	    

    @Autowired
    public TeacherService(TeacherRepository trepo, ClassRepository crepo) {
        this.trepo = trepo;
        this.crepo = crepo;
    }

    public TeacherEntity getTeacherById(int tid) {
        return trepo.findById(tid).orElse(null);
    }

    // Create or insert a teacher record in tblteacher
    public TeacherEntity insertTeacher(TeacherEntity teacher) {
        return trepo.save(teacher);
    }

    // Get all teachers
    public List<TeacherEntity> getAllTeachers() {
        return trepo.findAll();
    }

    // Update a teacher record
    @SuppressWarnings("finally")
    public TeacherEntity updateTeacher(int tid, TeacherEntity newTeacherDetails) {
        TeacherEntity teacher = new TeacherEntity();
        try {
            // Search for the teacher by id
            teacher = trepo.findById(tid).orElseThrow(() -> new NoSuchElementException("Teacher " + tid + " does not exist"));

            // Update the record
            teacher.setFname(newTeacherDetails.getFname());
            teacher.setLname(newTeacherDetails.getLname());
            teacher.setEmail(newTeacherDetails.getEmail());
            teacher.setPassword(newTeacherDetails.getPassword());
            teacher.setDepartment(newTeacherDetails.getDepartment());
            // Update other fields as needed

        } finally {
            return trepo.save(teacher);
        }
    }

    // Delete a teacher
    public String deleteTeacher(int tid) {
        String msg = "";

        if (trepo.existsById(tid)) {
            trepo.deleteById(tid);
            msg = "Teacher " + tid + " is successfully deleted!";
        } else
            msg = "Teacher " + tid + " does not exist";
        return msg;
    }

    public void addClassToTeacher(int tid, ClassEntity newClass) {
        TeacherEntity teacher = trepo.findById(tid).orElse(null);

        if (teacher != null && newClass != null) {
            // Add the class to the teacher's list
            teacher.addClass(newClass);

            // Save the teacher with the updated class list
            trepo.save(teacher);

            // Update the class with the teacher information
            newClass.setTeacher(teacher);
            crepo.save(newClass);
        } else {
            // Handle the case where either the teacher or the class is null
            if (teacher == null) {
                // Handle null teacher
                System.out.println("Teacher with ID " + tid + " not found");
            }
            if (newClass == null) {
                // Handle null class
                System.out.println("Class is null");
            }
        }
    }

}