package com.aurionpro.service;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.dao.TeacherSalaryDao;
import com.aurionpro.model.TeacherSalary;

public class TeacherSalaryService {
	private TeacherSalaryDao salaryDao = new TeacherSalaryDao();

    public void addSalary(TeacherSalary salary) throws SQLException {
        if (salary.getSalaryAmount() <= 0) {
            throw new IllegalArgumentException("Salary must be positive");
        }
        salaryDao.addSalary(salary);
    }

    public List<TeacherSalary> getAllSalaries() throws SQLException {
        return salaryDao.getAllSalaries();
    }

    public TeacherSalary getSalariesByTeacherId(int teacherId) throws SQLException {
        return salaryDao.getSalaryById(teacherId);
    }
}

