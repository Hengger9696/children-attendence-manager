package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "SUBMISSIONS")
public class Submission {
	
	@Id
    @SequenceGenerator(name = "SUBMISSIONS_ID_GENERATOR", sequenceName = "SUBMISSION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMISSIONS_ID_GENERATOR")
    @Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DUE")
	private Date due;
	
	@Column(name = "CLASS_ID")
	private Integer classId;
	
	public Integer getId() {
		return id;
		 }
	
	public void setId(Integer id) {
		this.id = id;
		 }
	
	public String getName() {
		return name;
		 }
	
	public void setName(String name) {
		this.name = name;
		 }

	public Date getDue() {
		return due;
		 }
	
	public void setDue(Date due) {
		this.due = due;
		 }
	
	public Integer getClassId() {
		return classId;
		 }
	
	public void setClassId(Integer classId) {
		this.classId = classId;
		 }
}
