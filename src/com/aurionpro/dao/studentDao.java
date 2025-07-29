package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.studentModel;

public class studentDao {
	private static PreparedStatement preparedStatement = null;

	public List<studentModel> getAllStudents() {
		Connection connection = Database.getConnection();
		List<studentModel> students = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM Student_table");
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
//				System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getInt(3));
					studentModel student = new studentModel();
					student.setStudent_id(result.getInt(1));
					student.setStudent_name(result.getString(2));
					student.setStudent_rollnumber(result.getInt(3));
					student.setStudent_DOB(result.getDate(4));
					students.add(student);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return students;
	}

	public static boolean checkStudent(int id) {
		Connection connection = Database.getConnection();
		ResultSet receivedId;
		try {
			preparedStatement = connection.prepareStatement("SELECT student_id FROM Student_table WHERE student_id = ?");
			preparedStatement.setInt(1, id);
			receivedId = preparedStatement.executeQuery();
			if (!receivedId.next()) {
				System.out.println("Student not found");
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public studentModel getAStudent(int studentId){
		Connection connection = Database.getConnection();
		studentModel student = new studentModel();;
		if(connection!=null && checkStudent(studentId)) {
			try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Student_table WHERE student_id = ?");
			preparedStatement.setInt(1, studentId);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			student.setStudent_id(result.getInt(1));
			student.setStudent_name(result.getString(2));
			student.setStudent_rollnumber(result.getInt(3));
			student.setStudent_DOB(result.getDate(4));
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return student;
		
	}
}
