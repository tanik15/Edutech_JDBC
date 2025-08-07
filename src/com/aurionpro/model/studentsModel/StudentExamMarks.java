package com.aurionpro.model.studentsModel;

public class StudentExamMarks {
    private int studentId;
    private String studentName;
    private int examId;
    private String subjectName;
    private int marksObtained;
    private int maxMarks;

    public StudentExamMarks() {
    	
    }
    
    // Constructor
    public StudentExamMarks(int studentId, String studentName, int examId, String subjectName, int marksObtained, int maxMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.examId = examId;
        this.subjectName = subjectName;
        this.marksObtained = marksObtained;
        this.maxMarks = maxMarks;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    // toString method
    @Override
    public String toString() {
        return "StudentExamMarks{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", examId=" + examId +
                ", subjectName='" + subjectName + '\'' +
                ", marksObtained=" + marksObtained +
                ", maxMarks=" + maxMarks +
                '}';
    }
}
