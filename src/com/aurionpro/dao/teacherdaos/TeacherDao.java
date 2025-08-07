package com.aurionpro.dao.teacherdaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.DBUtil;
import com.aurionpro.database.Database;
import com.aurionpro.model.teachermodels.Teacher;

public class TeacherDao {
	 // 1. Add Teacher
    public boolean addTeacher(Teacher teacher) {
        String query = "INSERT INTO teacher (name, email, phone_number, is_active) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getPhoneNumber());
            stmt.setBoolean(4, teacher.isActive());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Get all teachers
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teacher WHERE is_active = TRUE";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setName(rs.getString("name"));
                teacher.setEmail(rs.getString("email"));
                teacher.setPhoneNumber(rs.getString("phone_number"));
                teacher.setActive(rs.getBoolean("is_active"));

                teachers.add(teacher);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    // 3. Get teacher by ID
    public Teacher getTeacherById(int id) {
    	String query = "SELECT * FROM teacher WHERE teacher_id = ? AND is_active = TRUE";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setName(rs.getString("name"));
                teacher.setEmail(rs.getString("email"));
                teacher.setPhoneNumber(rs.getString("phone_number"));
                teacher.setActive(rs.getBoolean("is_active"));

                return teacher;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Update teacher
    public boolean updateTeacher(Teacher teacher) {
        String query = "UPDATE teacher SET name=?, email=?, phone_number=?, is_active=? WHERE teacher_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getPhoneNumber());
            stmt.setBoolean(4, teacher.isActive());
            stmt.setInt(5, teacher.getTeacherId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Delete teacher
    public boolean deleteTeacher(int teacherId) {
        String query = "UPDATE teacher SET is_active = FALSE WHERE teacher_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, teacherId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }}

