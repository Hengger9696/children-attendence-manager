package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Child;

@Repository
public interface AttendanceRepository extends JpaRepository<Child, Integer> {
	List<Child> findByClassId(int classId);
}