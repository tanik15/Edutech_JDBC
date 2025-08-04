package com.aurionpro.model.teachermodels;

public class Teacher {
	private int teacherId;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isActive;

    
    public Teacher() {
    }

    
    public Teacher(int teacherId, String name, String email, String phoneNumber, boolean isActive) {
        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}

