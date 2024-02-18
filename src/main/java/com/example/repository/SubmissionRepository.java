package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
	
    List<Submission> findByClassId(int classId);
}


