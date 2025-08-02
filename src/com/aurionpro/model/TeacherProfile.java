package com.aurionpro.model;

import java.sql.Date;
import java.time.LocalDate;

public class TeacherProfile {
	 private int teacherId;
	    private String gender;
	    private LocalDate dob;
	    private String qualification;
	    private double experience;
	    private LocalDate joiningDate;

	    
	    public TeacherProfile() {
	    }

	    
	    public TeacherProfile(int teacherId, String gender, LocalDate dob, String qualification, double experience, LocalDate joiningDate) {
	        this.teacherId = teacherId;
	        this.gender = gender;
	        this.dob = dob;
	        this.qualification = qualification;
	        this.experience = experience;
	        this.joiningDate = joiningDate;
	    }

	    
	    public int getTeacherId() {
	        return teacherId;
	    }

	    public void setTeacherId(int teacherId) {
	        this.teacherId = teacherId;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public LocalDate getDob() {
	        return dob;
	    }

	    public void setDob(LocalDate date) {
	        this.dob = date;
	    }

	    public String getQualification() {
	        return qualification;
	    }

	    public void setQualification(String qualification) {
	        this.qualification = qualification;
	    }

	    public double getExperience() {
	        return experience;
	    }

	    public void setExperience(double experience) {
	        this.experience = experience;
	    }

	    public LocalDate getJoiningDate() {
	        return joiningDate;
	    }

	    public void setJoiningDate(LocalDate joiningDate) {
	        this.joiningDate = joiningDate;
	    }

	    @Override
	    public String toString() {
	        return "TeacherProfile{" +
	                "teacherId=" + teacherId +
	                ", gender='" + gender + '\'' +
	                ", dob=" + dob +
	                ", qualification='" + qualification + '\'' +
	                ", experience=" + experience +
	                ", joiningDate=" + joiningDate +
	                '}';
	    }
	}

