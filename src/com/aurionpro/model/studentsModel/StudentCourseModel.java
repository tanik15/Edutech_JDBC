package com.aurionpro.model.studentsModel;

public class StudentCourseModel {
	private int studentRollNumber;
	private String studentName;
	private String studentCourses;
	private String studentSubjects;
	private String studentSubjectsIds;
	public String getStudentSubjectsIds() {
		return studentSubjectsIds;
	}

	public void setStudentSubjectsIds(String studentSubjectsIds) {
		this.studentSubjectsIds = studentSubjectsIds;
	}

	private int studentFees;

	// Constructor
	public StudentCourseModel(int studentRollnumber, String studentName, String studentCourses, String studentSubjects,int studentFees) {
		this.studentRollNumber = studentRollnumber;
		this.studentName = studentName;
		this.studentCourses = studentCourses;
		this.studentSubjects = studentSubjects;
		this.studentFees= studentFees;
	}

	public int getStudentRollNumber() {
		return studentRollNumber;
	}

	public void setStudentRollNumber(int studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}

	public int getStudentFees() {
		return studentFees;
	}

	public void setStudentFees(int studentFees) {
		this.studentFees = studentFees;
	}

	// Default constructor
	public StudentCourseModel() {
    }

	// Getters and Setters
	public int getstudentRollNumber() {
		return studentRollNumber;
	}

	public void setstudentRollnumber(int studentRollnumber) {
		this.studentRollNumber = studentRollnumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(String studentCourses) {
		this.studentCourses = studentCourses;
	}

	public String getStudentSubjects() {
		return studentSubjects;
	}

	public void setStudentSubjects(String studentSubjects) {
		this.studentSubjects = studentSubjects;
	}

}
