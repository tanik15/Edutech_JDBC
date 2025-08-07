package com.aurionpro.dao.teacherdaos;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.DBUtil;
import com.aurionpro.database.Database;
import com.aurionpro.model.teachermodels.TeacherActivity;

public class TeacherActivityDao {
	private Connection conn = null;
	
	public void addActivity(TeacherActivity activity) {
        String sql = "INSERT INTO teacher_activities (teacher_id, activity_name, description, activity_date) VALUES (?, ?, ?, ?)";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, activity.getTeacherId());
            stmt.setString(2, activity.getActivityName());
            stmt.setString(3, activity.getDescription());
            stmt.setDate(4, Date.valueOf(activity.getActivityDate()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TeacherActivity> getAllActivities() {
        List<TeacherActivity> activities = new ArrayList<>();
        String sql = "SELECT * FROM teacher_activities";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TeacherActivity activity = new TeacherActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setTeacherId(rs.getInt("teacher_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setActivityDate(rs.getDate("activity_date").toLocalDate());

                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activities;
    }

    public List<TeacherActivity> getActivitiesByTeacherId(int teacherId) {
        List<TeacherActivity> activities = new ArrayList<>();
        String sql = "SELECT * FROM teacher_activities WHERE teacher_id = ?";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TeacherActivity activity = new TeacherActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setTeacherId(rs.getInt("teacher_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setActivityDate(rs.getDate("activity_date").toLocalDate());

                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activities;
    }
    
    public TeacherActivityDao() {
    	if(conn==null) {
			conn  = Database.getConnection();
		}
    }
}
