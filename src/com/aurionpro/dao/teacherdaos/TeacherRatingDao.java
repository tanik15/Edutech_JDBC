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
import com.aurionpro.database.Database;
import com.aurionpro.model.teachermodels.TeacherRating;

public class TeacherRatingDao {
	public void addRating(TeacherRating rating) {
        String sql = "INSERT INTO teacher_rating (teacher_id, rating_value, given_by, comment, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rating.getTeacherId());
            stmt.setFloat(2, rating.getRatingValue());
            stmt.setString(3, rating.getGivenBy());
            stmt.setString(4, rating.getComment());
            stmt.setDate(5, Date.valueOf(rating.getDate()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TeacherRating> getAllRatings() {
        List<TeacherRating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM teacher_rating";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TeacherRating rating = new TeacherRating();
                rating.setRatingId(rs.getInt("rating_id"));
                rating.setTeacherId(rs.getInt("teacher_id"));
                rating.setRatingValue(rs.getFloat("rating_value"));
                rating.setGivenBy(rs.getString("given_by"));
                rating.setComment(rs.getString("comment"));
                rating.setDate(rs.getDate("date").toLocalDate());

                ratings.add(rating);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }

    public List<TeacherRating> getRatingsByTeacherId(int teacherId) {
        List<TeacherRating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM teacher_rating WHERE teacher_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TeacherRating rating = new TeacherRating();
                rating.setRatingId(rs.getInt("rating_id"));
                rating.setTeacherId(rs.getInt("teacher_id"));
                rating.setRatingValue(rs.getFloat("rating_value"));
                rating.setGivenBy(rs.getString("given_by"));
                rating.setComment(rs.getString("comment"));
                rating.setDate(rs.getDate("date").toLocalDate());

                ratings.add(rating);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }
}

