package com.aurionpro.dao.teacherdaos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.DBUtil;
import com.aurionpro.model.teachermodels.TeacherSalary;

public class TeacherSalaryDao {
	public void addSalary(TeacherSalary salary) {
        String sql = "INSERT INTO teacher_salary (teacher_id, salary_amount, pay_date, bonus, deductions) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salary.getTeacherId());
            stmt.setDouble(2, salary.getSalaryAmount());
            stmt.setDate(3, Date.valueOf(salary.getPayDate()));
            stmt.setDouble(4, salary.getBonus());
            stmt.setDouble(5, salary.getDeductions());
            stmt.executeUpdate();
            System.out.println("Salary added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TeacherSalary> getAllSalaries() {
        List<TeacherSalary> salaryList = new ArrayList<>();
        String sql = "SELECT * FROM teacher_salary";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TeacherSalary salary = new TeacherSalary();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setTeacherId(rs.getInt("teacher_id"));
                salary.setSalaryAmount(rs.getDouble("salary_amount"));
                salary.setPayDate(rs.getDate("pay_date").toLocalDate());
                salary.setBonus(rs.getDouble("bonus"));
                salary.setDeductions(rs.getDouble("deductions"));
                salaryList.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaryList;
    }

    public TeacherSalary getSalaryById(int salaryId) {
        String sql = "SELECT * FROM teacher_salary WHERE salary_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salaryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TeacherSalary salary = new TeacherSalary();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setTeacherId(rs.getInt("teacher_id"));
                salary.setSalaryAmount(rs.getDouble("salary_amount"));
                salary.setPayDate(rs.getDate("pay_date").toLocalDate());
                salary.setBonus(rs.getDouble("bonus"));
                salary.setDeductions(rs.getDouble("deductions"));
                return salary;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSalary(TeacherSalary salary) {
        String sql = "UPDATE teacher_salary SET teacher_id=?, salary_amount=?, pay_date=?, bonus=?, deductions=? WHERE salary_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salary.getTeacherId());
            stmt.setDouble(2, salary.getSalaryAmount());
            stmt.setDate(3, Date.valueOf(salary.getPayDate()));
            stmt.setDouble(4, salary.getBonus());
            stmt.setDouble(5, salary.getDeductions());
            stmt.setInt(6, salary.getSalaryId());
            stmt.executeUpdate();
            System.out.println("Salary updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSalary(int salaryId) {
        String sql = "DELETE FROM teacher_salary WHERE salary_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salaryId);
            stmt.executeUpdate();
            System.out.println("Salary deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

