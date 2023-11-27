package com.group1.citchecker.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.TeacherEntity;
import com.group1.citchecker.Repository.ClassRepository;

@Service
public class ClassService {

    @Autowired
    private ClassRepository crepo;

    // Create or insert a class record in tblclass
    public ClassEntity insertClass(ClassEntity classes) {
        return crepo.save(classes);
    }

    // Get all classes
    public List<ClassEntity> getAllClasses() {
        return crepo.findAll();
    }

    // Update a teacher record
    @SuppressWarnings("finally")
	public ClassEntity updateClass( int sid, ClassEntity newClassDetails) {
		ClassEntity classes = new ClassEntity();
        try {
            // Search for the teacher by id
        	classes = crepo.findById(sid).get();

            // Update the record
        	classes.setCode(newClassDetails.getCode());
        	classes.setCourse(newClassDetails.getCourse());
        	classes.setSchedule(newClassDetails.getSchedule());
        	classes.setRoom(newClassDetails.getRoom());
        	classes.setTime(newClassDetails.getTime());
            // Update other fields as needed

        }catch(NoSuchElementException ex) {
			throw new NoSuchElementException("Class" +sid + "does not exist!");	
		}finally {
			return crepo.save(classes);
		}
    }
    
	//D- delete a student
	public String deleteClass(int sid) {
		String msg ="";
		
		if(crepo.findById(sid) !=null) {
			crepo.deleteById(sid);
			msg = "Class " + sid + " is successfully deleted!";
		}else
			msg = "Class "+ sid + " does not exist";
		return msg;
	}

	public ClassEntity getClassById(int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveClass(ClassEntity classEntity) {
		// TODO Auto-generated method stub
		
	}
	

	public ClassEntity getTeacherById(int tid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveTeacher(TeacherEntity teacherEntity) {
		// TODO Auto-generated method stub
		
	}
}