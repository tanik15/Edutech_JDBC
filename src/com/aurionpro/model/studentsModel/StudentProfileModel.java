package com.aurionpro.model.studentsModel;

public class StudentProfileModel {
	private int studentId;
	private String studentAddress;
	private String studentGender;
	private String studentPhone;
	private String studentEmail;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public StudentProfileModel(int studentId, String studentAddress, String studentGender, String studentPhone,
			String studentEmail) {
		super();
		this.studentId = studentId;
		this.studentAddress = studentAddress;
		this.studentGender = studentGender;
		this.studentPhone = studentPhone;
		this.studentEmail = studentEmail;
	}
	public StudentProfileModel() {
		super();
	}
	

}
