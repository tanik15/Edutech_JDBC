package com.aurionpro.service.teacherservices;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.aurionpro.dao.teacherdaos.TeacherDao;
import com.aurionpro.dao.teacherdaos.TeacherSalaryDao;
import com.aurionpro.model.teachermodels.Teacher;
import com.aurionpro.model.teachermodels.TeacherSalary;

public class TeacherSalaryService {
    private TeacherSalaryDao salaryDao = new TeacherSalaryDao();
    private TeacherDao teacherDao = new TeacherDao();

    public void addSalary(TeacherSalary salary) throws Exception {
        validateSalary(salary);
        salaryDao.addSalary(salary);
        System.out.println("✅ Salary record added successfully.");
    }

    private void validateSalary(TeacherSalary salary) throws Exception {
        Teacher teacher = teacherDao.getTeacherById(salary.getTeacherId());
        if (teacher == null) {
            throw new IllegalArgumentException("❌ Invalid Teacher ID: No teacher found.");
        }

        if (salary.getTeacherId() <= 0) {
            throw new IllegalArgumentException("❌ Teacher ID must be positive.");
        }

        if (salary.getSalaryAmount() <= 0) {
            throw new IllegalArgumentException("❌ Salary must be greater than 0.");
        }

        if (salary.getBonus() < 0) {
            throw new IllegalArgumentException("❌ Bonus cannot be negative.");
        }

        if (salary.getDeductions() < 0) {
            throw new IllegalArgumentException("❌ Deductions cannot be negative.");
        }

        if (salary.getPayDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("❌ Pay Date cannot be in the future.");
        }
    }

    public List<TeacherSalary> getAllSalaries() throws SQLException {
        return salaryDao.getAllSalaries();
    }

    public TeacherSalary getSalariesByTeacherId(int salaryId) throws SQLException {
        return salaryDao.getSalaryById(salaryId);
    }
}
