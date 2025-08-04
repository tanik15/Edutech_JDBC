package com.aurionpro.model.teachermodels;

public class TeacherSubject {
	private int teacherId;
    private String subjectId;

    public TeacherSubject(int teacherId, String subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    @Override
    public String toString() {
        return String.format("| %-10d | %-15s |", teacherId, subjectId);
    }
}

