package com.aurionpro.service.teacherservices;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import com.aurionpro.dao.teacherdaos.TeacherDao;
import com.aurionpro.model.teachermodels.Teacher;

public class TeacherService {
    private TeacherDao teacherDao;

    public TeacherService() {
        teacherDao = new TeacherDao();
    }

    public boolean addTeacher(Teacher teacher) throws SQLException {
        validateTeacher(teacher);
        return teacherDao.addTeacher(teacher);
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        return teacherDao.getAllTeachers();
    }

    public Teacher getTeacherById(int id) throws SQLException {
        return teacherDao.getTeacherById(id);
    }

    public boolean deleteTeacher(int id) {
        return teacherDao.deleteTeacher(id);  
    }

   
    private void validateTeacher(Teacher teacher) {
        if (teacher.getName() == null || teacher.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be empty");
        }

        if (teacher.getEmail() == null || !isValidEmail(teacher.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (teacher.getPhoneNumber() == null || !isValidPhone(teacher.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }
    }

   
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}
