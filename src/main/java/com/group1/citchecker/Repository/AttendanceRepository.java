package com.group1.citchecker.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group1.citchecker.Entity.AttendanceEntity;

@Repository
public interface AttendanceRepository extends JpaRepository <AttendanceEntity, Integer> {

	List<AttendanceEntity> findByStudents_SidAndDate(int sid, String date);

	List<AttendanceEntity> findByClasses_CidAndDate(int cid, String date);

	

}