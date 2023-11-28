package com.group1.citchecker.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tblteacher")

public class TeacherEntity {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="teacher_id")
	private int tid;
	
	@Column(name="firstname")
	private String fname;
	
	@Column(name="lastname")
	private String lname;
	
	private String email;
	
	private String password;
	
	private String department;
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<ClassEntity> classes;

	public TeacherEntity() {
		super();
		this.classes = new ArrayList<>();
	}

	public TeacherEntity(int tid, String fname, String lname, String email, 
			String password, String department) {
		
		super();
		this.tid = tid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.department = department;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }

	public void addClass(ClassEntity newClass) {
		if (newClass != null) {
            // Ensure that the classes list is initialized
            if (classes == null) {
                classes = new ArrayList<>();
            }

            // Add the new class to the teacher's classes list
            classes.add(newClass);

            // Set the teacher for the new class
            newClass.setTeacher(this);
        }
    }
		
}