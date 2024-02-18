package com.example.form;

import java.util.List;

public class SubmissionDTO {
    private List<SubmittedChilerenList> submittedList;
    private Integer id;

    public List<SubmittedChilerenList> getSubmittedList() {
        return submittedList;
    }

    public void setSubmittedList(List<SubmittedChilerenList> submittedList) {
        this.submittedList = submittedList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}