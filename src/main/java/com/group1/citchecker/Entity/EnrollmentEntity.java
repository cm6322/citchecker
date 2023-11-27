package com.group1.citchecker.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tblenrollement")
public class EnrollmentEntity {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "enrollment_id")
	    private int id;

	    @ManyToOne
	    @JoinColumn(name = "class_id")
	    private ClassEntity classEntity;

	    @ManyToOne
	    @JoinColumn(name = "student_id")
	    private StudentEntity studentEntity;

	    public EnrollmentEntity() {
	        // Default constructor
	    }

	    public EnrollmentEntity(ClassEntity classEntity, StudentEntity studentEntity) {
	        this.classEntity = classEntity;
	        this.studentEntity = studentEntity;
	    }

		public Object getClassEntity() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getStudentEntity() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setClassEntity(Object classEntity2) {
			// TODO Auto-generated method stub
			
		}

		public void setStudentEntity(Object studentEntity2) {
			// TODO Auto-generated method stub
			
		}

}
