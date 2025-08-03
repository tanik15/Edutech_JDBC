package com.aurionpro.model.studentsModel;

import java.time.LocalDate;

public class StudentExamModel {
    private int examId;
    private String subjectId;
    private String examDate;
    private int totalMarks;
    private String semester;

    public StudentExamModel(int examId, String subjectId, String examDate, int totalMarks, String semester) {
        this.examId = examId;
        this.subjectId = subjectId;
        this.examDate = examDate;
        this.totalMarks = totalMarks;
        this.semester = semester;
    }

    public StudentExamModel() {}

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

}
