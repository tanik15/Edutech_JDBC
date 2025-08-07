package com.aurionpro.model.studentsModel;


public class StudentMarks {
    private int studentId;
    private int marksObtained;
    private int examId;

    // Constructor
    public StudentMarks(int studentId, int marksObtained, int examId) {
        this.studentId = studentId;
        this.marksObtained = marksObtained;
        this.examId = examId;
    }

    // Default Constructor
    public StudentMarks() {
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

    public int getexamId() {
        return examId;
    }

    public void setexamId(int examId) {
        this.examId = examId;
    }

    // toString method
    @Override
    public String toString() {
        return "StudentMarks{" +
                "studentId=" + studentId +
                ", marksObtained=" + marksObtained +
                ", examId='" + examId + '\'' +
                '}';
    }
}
