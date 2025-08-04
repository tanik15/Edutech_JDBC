package com.aurionpro.dao.teacherdaos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.DBUtil;
import com.aurionpro.model.teachermodels.TeacherAttendance;

public class TeacherAttendanceDao {
	public void addAttendance(TeacherAttendance attendance) {
        String sql = "INSERT INTO teacher_attendance (teacher_id, date, status, remarks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendance.getTeacherId());
            stmt.setDate(2, Date.valueOf(attendance.getDate()));
            stmt.setString(3, attendance.getStatus());
            stmt.setString(4, attendance.getRemarks());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TeacherAttendance> getAllAttendances() {
        List<TeacherAttendance> list = new ArrayList<>();
        String sql = "SELECT * FROM teacher_attendance";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TeacherAttendance attendance = new TeacherAttendance();
                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setTeacherId(rs.getInt("teacher_id"));
                attendance.setDate(rs.getDate("date").toLocalDate());
                attendance.setStatus(rs.getString("status"));
                attendance.setRemarks(rs.getString("remarks"));

                list.add(attendance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public TeacherAttendance getAttendanceById(int id) {
        String sql = "SELECT * FROM teacher_attendance WHERE attendance_id = ?";
        TeacherAttendance attendance = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                attendance = new TeacherAttendance();
                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setTeacherId(rs.getInt("teacher_id"));
                attendance.setDate(rs.getDate("date").toLocalDate());
                attendance.setStatus(rs.getString("status"));
                attendance.setRemarks(rs.getString("remarks"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendance;
    }

    public void deleteAttendance(int id) {
        String sql = "DELETE FROM teacher_attendance WHERE attendance_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAttendance(TeacherAttendance attendance) {
        String sql = "UPDATE teacher_attendance SET teacher_id = ?, date = ?, status = ?, remarks = ? WHERE attendance_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendance.getTeacherId());
            stmt.setDate(2, Date.valueOf(attendance.getDate()));
            stmt.setString(3, attendance.getStatus());
            stmt.setString(4, attendance.getRemarks());
            stmt.setInt(5, attendance.getAttendanceId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

