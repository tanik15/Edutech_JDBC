package com.aurionpro.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.aurionpro.dao.TeacherAttendanceDao;
import com.aurionpro.model.TeacherAttendance;

public class TeacherAttendanceService {
	 private TeacherAttendanceDao attendanceDao = new TeacherAttendanceDao();

	    // Allowed status values
	    private static final Set<String> VALID_STATUSES = Set.of("Present", "Absent", "On Leave");

	    public void markAttendance(TeacherAttendance attendance) throws SQLException {
	        String status = attendance.getStatus();

	        // Validate status before inserting
	        if (!VALID_STATUSES.contains(status)) {
	            throw new IllegalArgumentException("Invalid attendance status: " + status);
	        }

	        attendanceDao.addAttendance(attendance);
	    }

	    public TeacherAttendance getAttendanceByTeacherId(int teacherId) throws SQLException {
	        return attendanceDao.getAttendanceById(teacherId);
	    }

	    public List<TeacherAttendance> getAllAttendance() throws SQLException {
	        return attendanceDao.getAllAttendances();
	    }
	}

