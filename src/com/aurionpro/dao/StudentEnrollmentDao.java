package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.aurionpro.database.Database;
import com.aurionpro.model.studentsModel.StudentEnrollmentModel;

public class StudentEnrollmentDao {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;

	public static void addNewStudentEnrollment(StudentEnrollmentModel studentEnroll) {
		connection = Database.getConnection();
		if (connection != null && studentDao.checkStudent(studentEnroll.getStudentId()))
			try {
				preparedStatement = connection
						.prepareStatement("SELECT student_id FROM enrollment_table where student_id = ?");
				preparedStatement.setInt(1, studentEnroll.getStudentId());
				ResultSet rs = preparedStatement.executeQuery();
				if (!rs.next()) {
					preparedStatement = connection.prepareStatement(
							"INSERT INTO enrollment_table(student_id,class_id,enrollment_date,academic_year,enrollment_status) VALUES (?,?,?,?,?)");
					preparedStatement.setInt(1, studentEnroll.getStudentId());
					preparedStatement.setString(2, studentEnroll.getClassId());
					preparedStatement.setString(3, studentEnroll.getEnrollmentDate());
					preparedStatement.setString(4, studentEnroll.getAcademicYear());
					preparedStatement.setString(5, studentEnroll.getEnrollmentStatus());
					int update = preparedStatement.executeUpdate();
					System.out.println(update + " student Enrollment details is added");
					return;
				}
				System.out.println("Student Enrollment Details Already present. Want to update (y/n)?");
				Scanner scanner = new Scanner(System.in);
				String choice = scanner.nextLine();
				scanner.close();
				if (choice.equalsIgnoreCase("y")) {
					preparedStatement = connection.prepareStatement(
							"UPDATE enrollment_table SET class_id =?,enrollment_date=?,academic_year=?,enrollment_status=? where student_id=?");
					preparedStatement.setString(1, studentEnroll.getClassId());
					preparedStatement.setString(2, studentEnroll.getEnrollmentDate());
					preparedStatement.setString(3, studentEnroll.getAcademicYear());
					preparedStatement.setString(4, studentEnroll.getEnrollmentStatus());
					preparedStatement.setInt(5, studentEnroll.getStudentId());
					int update = preparedStatement.executeUpdate();
					System.out.println(update + " student Enrollment Detail is updated");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void updateStudentEnrollmentDetails(StudentEnrollmentModel studentEnroll) {
		connection = Database.getConnection();
		try {
			preparedStatement = connection.prepareStatement("SELECT student_id FROM enrollment_table where student_id = ?");
			preparedStatement.setInt(1, studentEnroll.getStudentId());
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {
				System.out.println("No Enrollment Details found for studentId : " + studentEnroll.getStudentId());
				return;
			}
			preparedStatement = connection.prepareStatement(
					"UPDATE enrollment_table SET class_id =?,enrollment_date=?,academic_year=?,enrollment_status=? where student_id=?");
			preparedStatement.setString(1, studentEnroll.getClassId());
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
}
