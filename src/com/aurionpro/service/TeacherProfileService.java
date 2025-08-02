package com.aurionpro.service;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.dao.TeacherProfileDao;
import com.aurionpro.model.TeacherProfile;

public class TeacherProfileService {
	 private TeacherProfileDao profileDao = new TeacherProfileDao();

	    public void addProfile(TeacherProfile profile) throws SQLException {
	        if (profile.getQualification() == null || profile.getQualification().isEmpty()) {
	            throw new IllegalArgumentException("Qualification cannot be empty");
	        }
	        if (profile.getExperience() < 0) {
	            throw new IllegalArgumentException("Experience cannot be negative");
	        }

	        profileDao.addTeacherProfile(profile);
	    }

	    public List<TeacherProfile> getAllProfiles() throws SQLException {
	        return profileDao.getAllProfiles();
	    }

	    public TeacherProfile getProfileByTeacherId(int teacherId) throws SQLException {
	        return profileDao.getProfileByTeacherId(teacherId);
	    }
	}

