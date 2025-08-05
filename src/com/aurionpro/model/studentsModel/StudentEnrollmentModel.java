package com.aurionpro.model.studentsModel;

import java.time.LocalDate;

public class StudentEnrollmentModel {
    private int studentId;
    private int classId;
    private String enrollmentDate;
    private String academicYear;
    private String enrollmentStatus;

    public StudentEnrollmentModel(int studentId, int classId, String enrollmentDate, String academicYear, String enrollmentStatus) {

        this.studentId = studentId;
        this.classId = classId;
        this.enrollmentDate = enrollmentDate;
        this.academicYear = academicYear;
        this.enrollmentStatus = enrollmentStatus;
    }

    public StudentEnrollmentModel() {}

 

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

}
