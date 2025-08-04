package com.aurionpro.model.teachermodels;

import java.time.LocalDate;

public class TeacherAttendance {
	private int attendanceId;
    private int teacherId;
    private LocalDate date;
    private String status;
    private String remarks;

  
    public TeacherAttendance() {
    }

    
    public TeacherAttendance(int attendanceId, int teacherId, LocalDate date, String status, String remarks) {
        this.attendanceId = attendanceId;
        this.teacherId = teacherId;
        this.date = date;
        this.status = status;
        this.remarks = remarks;
    }

   
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "TeacherAttendance{" +
                "attendanceId=" + attendanceId +
                ", teacherId=" + teacherId +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
