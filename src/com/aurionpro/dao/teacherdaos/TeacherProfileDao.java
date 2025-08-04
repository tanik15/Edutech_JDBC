package com.aurionpro.dao.teacherdaos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.DBUtil;
import com.aurionpro.model.teachermodels.TeacherProfile;

public class TeacherProfileDao {
	// 1. Insert profile
    public boolean addTeacherProfile(TeacherProfile profile) {
        String query = "INSERT INTO teacher_profile (teacher_id, gender, dob, qualification, experience, joining_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, profile.getTeacherId());
            stmt.setString(2, profile.getGender());
            stmt.setDate(3, Date.valueOf(profile.getDob()));
            stmt.setString(4, profile.getQualification());
            stmt.setDouble(5, profile.getExperience());
            stmt.setDate(6, Date.valueOf(profile.getJoiningDate()));

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Get profile by teacher ID
    public TeacherProfile getProfileByTeacherId(int teacherId) {
        String query = "SELECT * FROM teacher_profile WHERE teacher_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToProfile(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 3. Get all profiles
    public List<TeacherProfile> getAllProfiles() {
        List<TeacherProfile> profiles = new ArrayList<>();
        String query = "SELECT * FROM teacher_profile";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                profiles.add(mapResultSetToProfile(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profiles;
    }

    // 4. Update profile
    public boolean updateTeacherProfile(TeacherProfile profile) {
        String query = "UPDATE teacher_profile SET gender = ?, dob = ?, qualification = ?, experience = ?, joining_date = ? WHERE teacher_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, profile.getGender());
            stmt.setDate(2, Date.valueOf(profile.getDob()));
            stmt.setString(3, profile.getQualification());
            stmt.setDouble(4, profile.getExperience());
            stmt.setDate(5, Date.valueOf(profile.getJoiningDate()));
            stmt.setInt(6, profile.getTeacherId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 5. Delete profile (optional, if required)
    public boolean deleteTeacherProfile(int teacherId) {
        String query = "DELETE FROM teacher_profile WHERE teacher_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Utility function to convert ResultSet into TeacherProfile object
    private TeacherProfile mapResultSetToProfile(ResultSet rs) throws SQLException {
        TeacherProfile profile = new TeacherProfile();
        profile.setTeacherId(rs.getInt("teacher_id"));
        profile.setGender(rs.getString("gender"));
        profile.setDob(rs.getDate("dob").toLocalDate());
        profile.setQualification(rs.getString("qualification"));
        profile.setExperience(rs.getDouble("experience"));
        profile.setJoiningDate(rs.getDate("joining_date").toLocalDate());
        return profile;
    }
}
