package com.aurionpro.dao.studentsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.studentsModel.StudentExamModel;

public class StudentExamDao {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;
	
	public static void addNewExam(StudentExamModel examModel) {
		connection = Database.getConnection();
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO exam_table(subject_id,exam_date,total_marks,semester) VALUES (?,?,?,?)");
			preparedStatement.setString(1, examModel.getSubjectId());
			preparedStatement.setString(2, examModel.getExamDate());
			preparedStatement.setInt(3, examModel.getTotalMarks());
			preparedStatement.setString(4, examModel.getSemester());
			int update = preparedStatement.executeUpdate();
			System.out.println(update + " student Enrollment details is added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static List<StudentExamModel> getAllExams() {
		connection = Database.getConnection();
		List<StudentExamModel> exams = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM exam_table");
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					StudentExamModel exam = new StudentExamModel();
					exam.setExamId(result.getInt(1));
					exam.setSubjectId(result.getString(2));
					exam.setExamDate(result.getString(3));
					exam.setTotalMarks(result.getInt(4));
					exam.setSemester(result.getString(5));
					exams.add(exam);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return exams;
	}
}
