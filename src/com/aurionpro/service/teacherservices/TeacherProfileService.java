package com.aurionpro.service.teacherservices;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.aurionpro.dao.teacherdaos.TeacherDao;
import com.aurionpro.dao.teacherdaos.TeacherProfileDao;
import com.aurionpro.model.teachermodels.TeacherProfile;

public class TeacherProfileService {
    private TeacherProfileDao profileDao = new TeacherProfileDao();
    private TeacherDao teacherDao = new TeacherDao(); // Needed to check if Teacher ID exists

    public void addProfile(TeacherProfile profile) throws SQLException {
        validateProfile(profile); // run all validations
        profileDao.addTeacherProfile(profile);
    }

    public List<TeacherProfile> getAllProfiles() throws SQLException {
        return profileDao.getAllProfiles();
    }

    public TeacherProfile getProfileByTeacherId(int teacherId) throws SQLException {
        return profileDao.getProfileByTeacherId(teacherId);
    }


    private void validateProfile(TeacherProfile profile) throws SQLException {
      
        boolean teacherExists = teacherDao.getTeacherById(profile.getTeacherId()) != null;
        if (!teacherExists) {
            throw new IllegalArgumentException("Teacher ID " + profile.getTeacherId() + " does not exist.");
        }

        
        if (profile.getQualification() == null || profile.getQualification().trim().isEmpty()) {
            throw new IllegalArgumentException("Qualification cannot be empty.");
        }

        
        if (profile.getExperience() < 0) {
            throw new IllegalArgumentException("Experience cannot be negative.");
        }

       
        List<String> validGenders = Arrays.asList("Male", "Female");
        String gender = profile.getGender().trim();
        boolean isValidGender = validGenders.stream()
                .anyMatch(valid -> valid.equalsIgnoreCase(gender));
        if (!isValidGender) {
            throw new IllegalArgumentException("Gender must be 'Male' or 'Female' only.");
        }

      
        if (profile.getDob() == null || !profile.getDob().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth must be in the past.");
        }

    
        if (profile.getJoiningDate() == null || !profile.getJoiningDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Joining date must be in the past.");
        }
    }
}
