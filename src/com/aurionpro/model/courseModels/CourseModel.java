package com.aurionpro.model.courseModels;

public class CourseModel {
    private String courseId;
    private String courseName;
    private int courseDuration; // in years
    private String description;
    private int courseFees;

    public CourseModel() {}

    public CourseModel(String courseId, String courseName, int courseDuration, String description, int courseFees) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.description = description;
        this.courseFees = courseFees;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(int courseFees) {
        this.courseFees = courseFees;
    }

}
