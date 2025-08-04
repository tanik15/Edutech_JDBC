package com.aurionpro.model.courseModels;

public class CourseSubjectModel {
    private String subjectId;
    private String subjectName;
    private String semester;
    private int credits;

    public CourseSubjectModel() {}

    public CourseSubjectModel(String subjectId, String subjectName, String semester, int credits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.semester = semester;
        this.credits = credits;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "SubjectTable{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", semester='" + semester + '\'' +
                ", credits=" + credits +
                '}';
    }
}

