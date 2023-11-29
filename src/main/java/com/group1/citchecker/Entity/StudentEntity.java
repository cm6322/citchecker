package com.group1.citchecker.Entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tblstudent")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int sid;

    @Column(name = "firstname")
    private String fname;

    @Column(name = "lastname")
    private String lname;

    private String email;

    private String password;

    private String program;
    
    @OneToMany
    private List<ClassEntity> classes;

    public StudentEntity() {
        super();
        this.classes = new ArrayList<>();
    }

    public StudentEntity(int sid, String fname, String lname, String email, String password, String program) {
        super();
        this.sid = sid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.program = program;
        this.classes = new ArrayList<>();
    }
    
    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }

    public void addClass(ClassEntity newClass) {
        if (newClass != null && !classes.contains(newClass)) {
            // Ensure that the classes list is initialized
            if (classes == null) {
                classes = new ArrayList<>();
            }

            // Add the new class to the teacher's classes list
            classes.add(newClass);

            // Set the teacher for the new class
            newClass.setStudent(this);
        }
    }
  
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

	public void addEnrollment(EnrollmentEntity enrollmentEntity) {
		// TODO Auto-generated method stub
		
	}

}