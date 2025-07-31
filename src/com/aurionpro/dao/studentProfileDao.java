package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.database.Database;
import com.aurionpro.model.studentsModel.StudentProfileModel;

public class studentProfileDao {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;

	public static void addStudentProfile(StudentProfileModel studentProfile) {
		connection = Database.getConnection();
		Scanner scanner = new Scanner(System.in);
		if (connection != null && StudentDao.checkStudent(studentProfile.getStudentId())) {
			try {
				preparedStatement = connection
						.prepareStatement("SELECT student_id FROM student_profile where student_id = ?");
				preparedStatement.setInt(1, studentProfile.getStudentId());
				ResultSet rs = preparedStatement.executeQuery();
				
				if (!rs.next()) {
					preparedStatement = connection.prepareStatement("INSERT INTO student_profile VALUES (?,?,?,?,?)");
					preparedStatement.setInt(1, studentProfile.getStudentId());
					preparedStatement.setString(2, studentProfile.getStudentAddress());
					preparedStatement.setString(3, studentProfile.getStudentGender());
					preparedStatement.setString(4, studentProfile.getStudentPhone());
					preparedStatement.setString(5, studentProfile.getStudentEmail());
					int update = preparedStatement.executeUpdate();
					System.out.println(update + " student profile is added");
					return;
				}
				String choice = "Y";
				
				if (choice.equalsIgnoreCase("y")) {
					preparedStatement = connection.prepareStatement(
							"UPDATE student_profile SET student_address =?,student_gender=?,student_phone_no=?,student_email=? where student_id=?");
					preparedStatement.setString(1, studentProfile.getStudentAddress());
					preparedStatement.setString(2, studentProfile.getStudentGender());
					preparedStatement.setString(3, studentProfile.getStudentPhone());
					preparedStatement.setString(4, studentProfile.getStudentEmail());
					preparedStatement.setInt(5, studentProfile.getStudentId());
					int update = preparedStatement.executeUpdate();
					System.out.println(update + " student profile is updated");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void updateStudentProfile(StudentProfileModel studentProfile) {
		connection = Database.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement("SELECT student_id FROM student_profile where student_id = ?");
			preparedStatement.setInt(1, studentProfile.getStudentId());
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {
				System.out.println("No Student Profile Exist with StudentId : " + studentProfile.getStudentId());
				return;
			}
			preparedStatement = connection.prepareStatement(
					"UPDATE student_profile SET student_address =?,student_gender=?,student_phone_no=?,student_email=? where student_id=?");
			preparedStatement.setString(1, studentProfile.getStudentAddress());
			preparedStatement.setString(2, studentProfile.getStudentGender());
			preparedStatement.setString(3, studentProfile.getStudentPhone());
			preparedStatement.setString(4, studentProfile.getStudentEmail());
			preparedStatement.setInt(5, studentProfile.getStudentId());
			int update = preparedStatement.executeUpdate();
			System.out.println(update + " student profile is updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<StudentProfileModel> getAllStudentProfiles() {
		connection = Database.getConnection();
		List<StudentProfileModel> studentProfiles = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM student_profile");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				StudentProfileModel studentProfile = new StudentProfileModel();
				studentProfile.setStudentId(rs.getInt(1));
				studentProfile.setStudentAddress(rs.getString(2));
				studentProfile.setStudentGender(rs.getString(3));
				studentProfile.setStudentPhone(rs.getString(4));
				studentProfile.setStudentEmail(rs.getString(5));
				studentProfiles.add(studentProfile);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentProfiles;
	}

}
