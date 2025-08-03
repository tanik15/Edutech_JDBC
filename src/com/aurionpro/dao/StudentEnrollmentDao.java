package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.database.Database;
import com.aurionpro.model.studentsModel.StudentEnrollmentModel;

public class StudentEnrollmentDao {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;

	public static void addNewStudentEnrollment(StudentEnrollmentModel studentEnroll,Scanner scanner) {
		connection = Database.getConnection();
		if (connection != null && StudentDao.checkStudent(studentEnroll.getStudentId()))
			try {
				preparedStatement = connection
						.prepareStatement("SELECT student_id FROM enrollment_table where student_id = ?");
				preparedStatement.setInt(1, studentEnroll.getStudentId());
				ResultSet rs = preparedStatement.executeQuery();
				if (!rs.next()) {
					preparedStatement = connection.prepareStatement(
							"INSERT INTO enrollment_table(student_id,class_id,enrollment_date,academic_year) VALUES (?,?,?,?)");
					preparedStatement.setInt(1, studentEnroll.getStudentId());
					preparedStatement.setInt(2, studentEnroll.getClassId());
					preparedStatement.setString(3, studentEnroll.getEnrollmentDate());
					preparedStatement.setString(4, studentEnroll.getAcademicYear());
					int update = preparedStatement.executeUpdate();
					System.out.println(update + " student Enrollment details is added");
					return;
				}
				System.out.println("Student Enrollment Details Already present. Want to update (y/n)?");

				String choice = scanner.nextLine();
				if (choice.equalsIgnoreCase("y")) {
					preparedStatement = connection.prepareStatement(
							"UPDATE enrollment_table SET class_id =?,enrollment_date=?,academic_year=? where student_id=?");
					preparedStatement.setInt(1, studentEnroll.getClassId());
					preparedStatement.setString(2, studentEnroll.getEnrollmentDate());
					preparedStatement.setString(3, studentEnroll.getAcademicYear());
					preparedStatement.setInt(4, studentEnroll.getStudentId());
					int update = preparedStatement.executeUpdate();
					System.out.println(update + " Student Enrollment Detail is Updated");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void updateStudentEnrollmentDetails(StudentEnrollmentModel studentEnroll) {
		connection = Database.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement("SELECT student_id FROM enrollment_table where student_id = ?");
			preparedStatement.setInt(1, studentEnroll.getStudentId());
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {
				System.out.println("No Enrollment Details found for studentId : " + studentEnroll.getStudentId());
				return;
			}
			preparedStatement = connection.prepareStatement(
					"UPDATE enrollment_table SET class_id =?,enrollment_date=?,academic_year=?,enrollment_status=? where student_id=?");
			preparedStatement.setInt(1, studentEnroll.getClassId());
			preparedStatement.setString(2, studentEnroll.getEnrollmentDate());
			preparedStatement.setString(3, studentEnroll.getAcademicYear());
			preparedStatement.setString(4, studentEnroll.getEnrollmentStatus());
			preparedStatement.setInt(5, studentEnroll.getStudentId());
			int update = preparedStatement.executeUpdate();
			System.out.println(update + " student Enrollment Detail is updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateEnrollmentStatus(int studentId,String status) {
		connection = Database.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement("SELECT student_id FROM enrollment_table where student_id = ?");
			preparedStatement.setInt(1, studentId);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {
				System.out.println("No Enrollment Details found for studentId : " + studentId);
				return;
			}
			preparedStatement = connection.prepareStatement(
					"UPDATE enrollment_table SET enrollment_status=? where student_id=?");
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, studentId);
			int update = preparedStatement.executeUpdate();
			System.out.println(update + " student Enrollment Status is updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<StudentEnrollmentModel> getAllEnrollmentDetails() {
		connection = Database.getConnection();
		List<StudentEnrollmentModel> studentEnrollments = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM Enrollment_table");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				StudentEnrollmentModel enrollment = new StudentEnrollmentModel();
				enrollment.setStudentId(rs.getInt(2));
				enrollment.setClassId(rs.getInt(3));
				enrollment.setEnrollmentDate(rs.getString(4));
				enrollment.setAcademicYear(rs.getString(5));
				enrollment.setEnrollmentStatus(rs.getString(6));
				studentEnrollments.add(enrollment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentEnrollments;
	}
}
