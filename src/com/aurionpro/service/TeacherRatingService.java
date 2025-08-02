package com.aurionpro.service;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.dao.TeacherRatingDao;
import com.aurionpro.model.TeacherRating;

public class TeacherRatingService {
	private TeacherRatingDao ratingDao = new TeacherRatingDao();

    public void addRating(TeacherRating rating) throws SQLException {
        if (rating.getRatingValue() < 1 || rating.getRatingValue() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        ratingDao.addRating(rating);
    }

    public List<TeacherRating> getAllRatings() throws SQLException {
        return ratingDao.getAllRatings();
    }

    public List<TeacherRating> getRatingsByTeacherId(int teacherId) throws SQLException {
        return ratingDao.getRatingsByTeacherId(teacherId);
    }
}

