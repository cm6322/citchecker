package com.group1.citchecker.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tblstudent")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private int sid;
	
	@Column(name="firstname")
	private String fname;
	
	@Column(name="lastname")
	private String lname;
	
	private String email;
	
	private String password;
	
	private String program;
	
	@ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;
	
	 @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
	    private List<EnrollmentEntity> enrollments;

	public StudentEntity() {
		super();
	}

	public StudentEntity(int sid, String fname, String lname, String gender, String email, 
			String password, String program) {
		super();
		this.sid = sid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.program = program;
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
	
	public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

	 public List<EnrollmentEntity> getEnrollments() {
	        return enrollments;
    }

	public void setEnrollments(List<EnrollmentEntity> enrollments) {
	        this.enrollments = enrollments;
	}
	
	
}