package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository trepo;

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
            teacher = trepo.findById(tid).get();

            // Update the record
            teacher.setFname(newTeacherDetails.getFname());
            teacher.setLname(newTeacherDetails.getLname());
            teacher.setEmail(newTeacherDetails.getEmail());
            teacher.setPassword(newTeacherDetails.getPassword());
            teacher.setDepartment(newTeacherDetails.getDepartment());
            // Update other fields as needed

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Teacher " + tid + " does not exist!");
        } finally {
            return trepo.save(teacher);
        }
    }

    // Delete a teacher
    public String deleteTeacher(int tid) {
        String msg = "";

        if (trepo.findById(tid).isPresent()) {
            trepo.deleteById(tid);
            msg = "Teacher " + tid + " is successfully deleted!";
        } else
            msg = "Teacher " + tid + " does not exist";
        return msg;
    }

    public TeacherEntity addClass(int tid, ClassEntity newClass) {
        TeacherEntity teacher = trepo.findById(tid).orElse(null);
        if (teacher != null) {
            // Add the new class to the teacher's classes list
            teacher.getClasses().add(newClass);
            // Set the teacher for the new class
            newClass.setTeacher(teacher);
            // Save both the teacher and the new class entities
            trepo.save(teacher);
            return teacher; // Return the updated teacher
        }
        return null;
    }

	public TeacherEntity getTeacherById(int tid) {
		 return trepo.findById(tid).orElse(null);
	}



  
}
