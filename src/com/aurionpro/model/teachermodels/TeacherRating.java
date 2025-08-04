package com.aurionpro.model.teachermodels;

import java.time.LocalDate;

public class TeacherRating {
	 private int ratingId;
	    private int teacherId;
	    private float ratingValue;
	    private String givenBy;
	    private String comment;
	    private LocalDate date;

	    
	    public TeacherRating() {
	    }

	    
	    public TeacherRating(int ratingId, int teacherId, float ratingValue, String givenBy, String comment, LocalDate date) {
	        this.ratingId = ratingId;
	        this.teacherId = teacherId;
	        this.ratingValue = ratingValue;
	        this.givenBy = givenBy;
	        this.comment = comment;
	        this.date = date;
	    }

	    
	    public int getRatingId() {
	        return ratingId;
	    }

	    public void setRatingId(int ratingId) {
	        this.ratingId = ratingId;
	    }

	    public int getTeacherId() {
	        return teacherId;
	    }

	    public void setTeacherId(int teacherId) {
	        this.teacherId = teacherId;
	    }

	    public float getRatingValue() {
	        return ratingValue;
	    }

	    public void setRatingValue(float ratingValue) {
	        this.ratingValue = ratingValue;
	    }

	    public String getGivenBy() {
	        return givenBy;
	    }

	    public void setGivenBy(String givenBy) {
	        this.givenBy = givenBy;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	        this.comment = comment;
	    }

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    @Override
	    public String toString() {
	        return "TeacherRating{" +
	                "ratingId=" + ratingId +
	                ", teacherId=" + teacherId +
	                ", ratingValue=" + ratingValue +
	                ", givenBy='" + givenBy + '\'' +
	                ", comment='" + comment + '\'' +
	                ", date=" + date +
	                '}';
	    }
}
