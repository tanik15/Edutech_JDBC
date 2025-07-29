package com.aurionpro.model;

import java.sql.Date;
import java.time.LocalDate;

public class studentModel {
	private int student_id;
	private String student_name;
	private int Student_rollnumber;
	private Date student_DOB;
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public int getStudent_rollnumber() {
		return Student_rollnumber;
	}
	public void setStudent_rollnumber(int student_rollnumber) {
		Student_rollnumber = student_rollnumber;
	}
	public Date getStudent_DOB() {
		return student_DOB;
	}
	public void setStudent_DOB(Date student_DOB) {
		this.student_DOB = student_DOB;
	}
	public studentModel() {
		super();
	}
	public studentModel(int student_id, String student_name, int student_rollnumber, Date student_DOB) {
		super();
		this.student_id = student_id;
		this.student_name = student_name;
		Student_rollnumber = student_rollnumber;
		this.student_DOB = student_DOB;
	}
}
