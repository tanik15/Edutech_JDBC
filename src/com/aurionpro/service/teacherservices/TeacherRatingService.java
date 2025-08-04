package com.aurionpro.service.teacherservices;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.dao.teacherdaos.TeacherRatingDao;
import com.aurionpro.model.teachermodels.TeacherRating;

public class TeacherRatingService {
	private TeacherRatingDao ratingDao = new TeacherRatingDao();

    public boolean addRating(TeacherRating rating) throws SQLException {
        if (rating.getRatingValue() < 1 || rating.getRatingValue() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        ratingDao.addRating(rating);
		return false;
    }

    public List<TeacherRating> getAllRatings() throws SQLException {
        return ratingDao.getAllRatings();
    }

    public List<TeacherRating> getRatingsByTeacherId(int teacherId) throws SQLException {
        return ratingDao.getRatingsByTeacherId(teacherId);
    }
}

