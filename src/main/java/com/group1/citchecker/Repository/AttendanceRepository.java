package com.group1.citchecker.Repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group1.citchecker.Entity.AttendanceEntity;

@Repository
public interface AttendanceRepository extends JpaRepository <AttendanceEntity, Integer> {

	List<AttendanceEntity> findByClasses_CidAndDate(int cid, LocalDate date);

	List<AttendanceEntity> findByStudents_Sid(int sid);


	

}