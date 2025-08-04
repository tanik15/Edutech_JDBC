package com.aurionpro.model.teachermodels;

import java.time.LocalDate;

public class TeacherActivity {
	private int activityId;
    private int teacherId;
    private String activityName;
    private String description;
    private LocalDate activityDate;

   
    public TeacherActivity() {
    }

    
    public TeacherActivity(int activityId, int teacherId, String activityName, String description, LocalDate activityDate) {
        this.activityId = activityId;
        this.teacherId = teacherId;
        this.activityName = activityName;
        this.description = description;
        this.activityDate = activityDate;
    }

    
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    @Override
    public String toString() {
        return "TeacherActivity{" +
                "activityId=" + activityId +
                ", teacherId=" + teacherId +
                ", activityName='" + activityName + '\'' +
                ", description='" + description + '\'' +
                ", activityDate=" + activityDate +
                '}';
    }
}
