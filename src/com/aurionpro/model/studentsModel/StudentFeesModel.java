package com.aurionpro.model.studentsModel;

public class StudentFeesModel {
    private int studentId;
    private String studentName;
    private String courseSelected;
    private double courseFees;

    // Constructors
    public StudentFeesModel() {
    }

    public StudentFeesModel(int studentId, String studentName, String courseSelected, double courseFees) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseSelected = courseSelected;
        this.courseFees = courseFees;
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

    public String getCourseSelected() {
        return courseSelected;
    }

    public void setCourseSelected(String courseSelected) {
        this.courseSelected = courseSelected;
    }

    public double getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(double courseFees) {
        this.courseFees = courseFees;
    }

}
