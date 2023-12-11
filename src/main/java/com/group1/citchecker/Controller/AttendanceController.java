package com.group1.citchecker.Controller;

import com.group1.citchecker.Entity.AttendanceEntity;
import com.group1.citchecker.Entity.ClassEntity;
import com.group1.citchecker.Entity.StudentEntity;
import com.group1.citchecker.Repository.AttendanceRepository;
import com.group1.citchecker.Repository.ClassRepository;
import com.group1.citchecker.Repository.StudentRepository;
import com.group1.citchecker.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository arepo;
    @Autowired
    private ClassRepository crepo;
    @Autowired
    private StudentRepository srepo;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/createAttendance")
public ResponseEntity<String> createAttendance(@RequestParam int classId, @RequestParam int studentId) {
    try {
        ClassEntity classEntity = crepo.findById(classId)
                .orElseThrow(() -> new NoSuchElementException("Class not found"));

        StudentEntity studentEntity = srepo.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));

        LocalDate currentDate = LocalDate.now();
        boolean attendanceExists = arepo.existsByStudentsAndClassesAndDate(studentEntity, classEntity, currentDate);
        
        if (attendanceExists) {
            return new ResponseEntity<>("Attendance record already exists for the student on the current date.", HttpStatus.BAD_REQUEST);
        } else {
            AttendanceEntity attendanceEntity = new AttendanceEntity();
            attendanceEntity.setDate(currentDate);
            attendanceEntity.setPresent(false);

            attendanceEntity.setClasses(classEntity);
            attendanceEntity.setStudents(studentEntity);

            attendanceService.insertAttendance(attendanceEntity);

            return new ResponseEntity<>("Attendance record created successfully!", HttpStatus.OK);
        }
    } catch (NoSuchElementException e) {
        return new ResponseEntity<>("Error creating attendance record: " + e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        return new ResponseEntity<>("Error creating attendance record: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @GetMapping("/getAllAttendance")
    public List<AttendanceEntity> getAllAttendances() {
        return attendanceService.getAllAttendance();
    }
    @GetMapping("/attendanceById/{aid}")
    public AttendanceEntity classById(@PathVariable int aid) {
        return attendanceService.getAttendanceById(aid);
    }
    @PutMapping("/updateAttendance/{id}")
    public AttendanceEntity updateStudent(@PathVariable int id, @RequestBody AttendanceEntity newAttendanceDetails) {
        return attendanceService.updateAttendance(id, newAttendanceDetails);
    }

    @DeleteMapping("/deleteAttendance/{id}")
    public String deleteStudent(@PathVariable int id) {
        return attendanceService.deleteAttendance(id);
    }

    @GetMapping("/byId/{id}")
    public AttendanceEntity getAttendanceById(@PathVariable int id) {
        return attendanceService.getAttendanceById(id);
    }

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestParam int attendanceId, @RequestParam boolean present) {
        try {
            AttendanceEntity attendance = attendanceService.getAttendanceById(attendanceId);

            if (attendance != null) {
                if(attendance.getPresent()){
                    attendance.setPresent(false);
                } else {
                    attendance.setPresent(true);
                }
                attendanceService.updateAttendance(attendance);
            
                return new ResponseEntity<>("Attendance marked successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Attendance record not found for ID: " + attendanceId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error marking attendance: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/byStudentId/{sid}")
    public List<AttendanceEntity> getAttendanceByStudentId(@PathVariable int sid) {
        return attendanceService.getAttendanceByStudentId(sid);
    }
    
}
