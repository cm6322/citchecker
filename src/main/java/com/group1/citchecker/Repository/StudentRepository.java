package com.group1.citchecker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.citchecker.Entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository <StudentEntity, Integer> {

}