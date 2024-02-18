package com.example.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubmissionEditForm {
	
	private String submissionName;
	private Date due;
	private List<SubmittedChilerenList> submittedChilerenListt = new ArrayList<>();
	
	public String getSubmissionName() {
		return submissionName;
	}
	
	public void setSubmissionName(String submissionName) {
		this.submissionName = submissionName;
	}
	
	public Date getDue() {
		return due;
	}
	
	public void setDue(Date due) {
		this.due = due;
	}
}
