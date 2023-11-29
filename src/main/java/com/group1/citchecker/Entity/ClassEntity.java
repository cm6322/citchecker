package com.group1.citchecker.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tblclass")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int sid;

    @Column(name = "subject_code")
    private String code;

    @Column(name = "course_name")
    private String course;

    private String schedule;
    private String room;
    private String time;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    public ClassEntity() {
        super();
    }

    public ClassEntity(int sid, String code, String course, String schedule, String room, String time) {
        super();
        this.sid = sid;
        this.code = code;
        this.course = course;
        this.schedule = schedule;
        this.room = room;
        this.time = time;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTeacher(TeacherEntity teacherEntity) {
    }

	public void setStudent(StudentEntity studentEntity) {
		// TODO Auto-generated method stub
		
	}

	public void addEnrollment(EnrollmentEntity enrollmentEntity) {
		// TODO Auto-generated method stub
		
	}
}
