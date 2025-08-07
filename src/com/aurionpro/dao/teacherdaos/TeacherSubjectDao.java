package com.aurionpro.dao.teacherdaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.teachermodels.TeacherSubject;

public class TeacherSubjectDao {

    
    private Connection conn;


    public TeacherSubjectDao() {
        this.conn = Database.getConnection();
    }

    // Assign subject to teacher
    public boolean assignSubjectToTeacher(int teacherId, String subjectId) {
        String sql = "INSERT INTO teacher_subject (teacher_id, subject_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            stmt.setString(2, subjectId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error assigning subject: " + e.getMessage());
            return false;
        }
    }

    // Get all teacher-subject assignments
    public List<TeacherSubject> getAllAssignments() {
        List<TeacherSubject> list = new ArrayList<>();
        String sql = "SELECT * FROM teacher_subject";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int teacherId = rs.getInt("teacher_id");
                String subjectId = rs.getString("subject_id");
                list.add(new TeacherSubject(teacherId, subjectId));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching assignments: " + e.getMessage());
        }
        return list;
    }
}
