package com.aurionpro.service.teacherservices;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.aurionpro.dao.teacherdaos.TeacherAttendanceDao;
import com.aurionpro.dao.teacherdaos.TeacherDao;
import com.aurionpro.model.teachermodels.TeacherAttendance;

public class TeacherAttendanceService {
    private TeacherAttendanceDao attendanceDao = new TeacherAttendanceDao();
    private TeacherDao teacherDao = new TeacherDao();  // Assuming this exists

    private static final Set<String> VALID_STATUSES = Set.of("Present", "Absent", "On Leave");

    public void markAttendance(TeacherAttendance attendance) throws SQLException {
        String status = attendance.getStatus();
        String remarks = attendance.getRemarks();
        int teacherId = attendance.getTeacherId();
        LocalDate date = attendance.getDate();

        // Status check (case-insensitive match)
        if (status == null || VALID_STATUSES.stream().noneMatch(s -> s.equalsIgnoreCase(status))) {
            throw new IllegalArgumentException("❌ Invalid attendance status. Allowed: Present, Absent, On Leave");
        }

        // Empty remarks check
        if (remarks == null || remarks.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Remarks cannot be empty.");
        }

        // Future date check
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("❌ Attendance date cannot be in the future.");
        }

        // Standardize status capitalization
        attendance.setStatus(capitalizeStatus(status));

        attendanceDao.addAttendance(attendance);
    }

    private String capitalizeStatus(String status) {
        return VALID_STATUSES.stream()
            .filter(s -> s.equalsIgnoreCase(status))
            .findFirst()
            .orElse(status);
    }

    public TeacherAttendance getAttendanceByTeacherId(int teacherId) throws SQLException {
        return attendanceDao.getAttendanceById(teacherId);
    }

    public List<TeacherAttendance> getAllAttendance() throws SQLException {
        return attendanceDao.getAllAttendances();
    }
}
