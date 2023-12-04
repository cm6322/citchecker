package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.StudentEntity;
import com.group1.citchecker.Repository.ClassRepository;
import com.group1.citchecker.Repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository srepo;
    

    
    @Autowired
    public StudentService(StudentRepository srepo, ClassRepository crepo) {
        this.srepo = srepo;
    }
    
    public StudentEntity getStudentById(int sid) {
		return srepo.findById(sid).orElse(null);
	}

    // create or insert student record in tblstudent
    public StudentEntity insertStudent(StudentEntity student) {
        return srepo.save(student);
    }

    // GET ALL STUDENTS
    public List<StudentEntity> getAllStudents() {
        return srepo.findAll();
    }

    // Update a student record
    @SuppressWarnings("finally")
    public StudentEntity updateStudent(int sid, StudentEntity newStudentDetails) {
        StudentEntity student = new StudentEntity();
        try {
            // search the id number of the student that will be updated
            student = srepo.findById(sid).get();

            // update the record
            student.setFname(newStudentDetails.getFname());
            student.setLname(newStudentDetails.getLname());
            student.setEmail(newStudentDetails.getEmail());
            student.setPassword(newStudentDetails.getPassword());
            student.setProgram(newStudentDetails.getProgram());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Student " + sid + " does not exist!");
        } finally {
            return srepo.save(student);
        }
    }

    // Delete a student
    public String deleteStudent(int sid) {
        String msg = "";

        if (srepo.findById(sid).isPresent()) {
            srepo.deleteById(sid);
            msg = "Student " + sid + " is successfully deleted!";
        } else
            msg = "Student " + sid + " does not exist";
        return msg;
    }
    public void addStudentsToClass(List<Integer> studentIds, ClassEntity newClass) {
        if (studentIds != null && newClass != null) {
            for (Integer studentId : studentIds) {
                StudentEntity student = srepo.findById(studentId).orElse(null);
                if (student != null) {
                    student.addClass(newClass);
                    srepo.save(student);
                } else {
                    // Handle null student
                    System.out.println("Student with ID " + studentId + " not found.");
                    // You might throw an exception or log the error depending on your requirements
                }
            }
        } else {
            // Handle null studentIds or class
            if (studentIds == null) {
                System.out.println("List of studentIds is null.");
                // You might throw an exception or log the error depending on your requirements
            }
            if (newClass == null) {
                System.out.println("ClassEntity is null.");
                // You might throw an exception or log the error depending on your requirements
            }
        }
    }
    
    public void addClassToStudent(int sid, ClassEntity newClass) {
        StudentEntity student = srepo.findById(sid).orElse(null);

        if (student != null && newClass != null) {
            student.addClass(newClass);
            srepo.save(student);
        } else {
            // Handle null student or class
            if (student == null) {
                System.out.println("Student with ID " + sid + " not found.");
                // You might throw an exception or log the error depending on your requirements
            }
            if (newClass == null) {
                System.out.println("ClassEntity is null.");
                // You might throw an exception or log the error depending on your requirements
            }
        }
    }



}