package com.example.form;

import java.util.Date;

public class SubmissionList {
	private Integer id;
	private String name;
	private Date due;
	private Integer count;
	
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
	
	public Integer getCount() {
		return count;
		 }
	
	public void setCount(Integer count) {
		this.count = count;
		 }
}
