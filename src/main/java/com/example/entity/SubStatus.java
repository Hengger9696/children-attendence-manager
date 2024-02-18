package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SUB_STATUS")
public class SubStatus {
	
    @Id
    @SequenceGenerator(name = "SUBSTATUS_ID_GENERATOR", sequenceName = "SUBSTATUS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SUBSTATUS_ID_GENERATOR")
    @Column(name = "ID")
	private Integer id;
    
    @Column(name = "CHILDREN_ID")
	private Integer childrenId;
    
    @Column(name = "SUBMISSION_ID")
	private Integer submissionId;
    
    @Column(name = "SUBMITTED")
	private Integer submitted;
	
	public Integer getId() {
		return id;
		 }
	
	public void setId(Integer id) {
		this.id = id;
		 }
	
	public Integer getChildrenId() {
		return childrenId;
		 }
	
	public void setChildrenId(Integer childrenId) {
		this.childrenId = childrenId;
		 }
	
	public Integer getSubmissionId() {
		return submissionId;
		 }
	
	public void setSubmissionId(Integer submisionId) {
		this.submissionId = submisionId;
		 }
	
	public Integer getSubmitted() {
		return submitted;
		 }
	
	public void setSubmitted(Integer submitted) {
		this.submitted = submitted;
		 }

}
