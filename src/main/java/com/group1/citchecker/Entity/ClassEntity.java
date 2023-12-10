package com.group1.citchecker.Entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tblclass")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int cid;

    @Column(name = "subject_code")
    private String code;

    @Column(name = "course_name")
    private String course;
    private String section;
    private String schedule;
    private String room;
    private String time;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private TeacherEntity teacher;

    private int tid;

    @ManyToMany
    private List<StudentEntity> students;

    public ClassEntity() {
        super();
        this.students = new ArrayList<>();

    }

    public ClassEntity(int cid, String code, String course, String schedule, String room, String time, String section) {
        super();
        this.cid = cid;
        this.code = code;
        this.course = course;
        this.section = section;
        this.schedule = schedule;
        this.room = room;
        this.time = time;
    }
    public List<StudentEntity> getStudents() {
        return students;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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
    
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public TeacherEntity getTeacher() {
        return teacher;
    }
    
    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public int getTid() {
        if(teacher != null) {
            return teacher.getTid();
        } else
        return 0;
    }

    public void setTid(int tid) {
        this.tid = teacher.getTid();
    }

	public void setStudent(StudentEntity studentEntity) {
		
	}
	
}