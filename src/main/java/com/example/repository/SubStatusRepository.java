package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.SubStatus;

@Repository
public interface SubStatusRepository extends JpaRepository<SubStatus, Integer> {
	
     List<SubStatus> findBySubmissionId(Integer submissionId);

     Integer countBySubmissionIdAndSubmitted(Integer submissionId, Integer submitted);
}
