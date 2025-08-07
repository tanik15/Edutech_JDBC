package com.aurionpro.dao.courseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.courseModels.CourseSubjectModel;
import com.aurionpro.model.studentsModel.StudentModel;

public class CourseSubjectDao {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;

	private static CourseSubjectModel createSubjectObj(ResultSet result) {
		CourseSubjectModel courseSubject = new CourseSubjectModel();
		try {
			courseSubject.setSubjectId(result.getString(1));
			courseSubject.setSubjectName(result.getString(2));
			courseSubject.setSemester(result.getString(3));
			courseSubject.setCredits(result.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseSubject;
	}

	public static List<CourseSubjectModel> getAllSubjects(boolean isactive) {
		connection = Database.getConnection();
		List<CourseSubjectModel> subjects = new ArrayList<>();
		if (connection != null) {
			try {
				
				preparedStatement = connection.prepareStatement("SELECT * FROM subject_table WHERE is_active ="+isactive);
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					CourseSubjectModel subject = createSubjectObj(result);
					subjects.add(subject);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subjects;
	}

	private static boolean checkSubject(String SubjectId) {
		connection = Database.getConnection();
		ResultSet receivedId;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM subject_table WHERE subject_id = ?");
			preparedStatement.setString(1, SubjectId);
			receivedId = preparedStatement.executeQuery();
			if (!receivedId.next()) {
				System.out.println("Subject not found");
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static CourseSubjectModel getASubject(String SubjectId) {
		connection = Database.getConnection();
		CourseSubjectModel subject = null;
		if (connection != null && checkSubject(SubjectId)) {
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM subject_table WHERE subject_id = ?");
				preparedStatement.setString(1, SubjectId);
				ResultSet result = preparedStatement.executeQuery();
				result.next();
				subject = createSubjectObj(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return subject;

	}

	public static void deleteSubject(String SubjectId) {
		try {
			connection = Database.getConnection();
			if (checkSubject(SubjectId)) {
				preparedStatement = connection
						.prepareStatement("UPDATE subject_table SET is_active = FALSE WHERE subject_id = ? ");
				preparedStatement.setString(1, SubjectId);
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println("\n✅ Subject with ID: " + SubjectId + " has been marked as Inactive.\n");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addNewSubject(CourseSubjectModel subject) {
		connection = Database.getConnection();
		try {
			if (!checkSubject(subject.getSubjectId())) {
				preparedStatement = connection.prepareStatement(
						"INSERT INTO subject_table(subject_id,subject_name,semester,credits) VALUES (?,?,?,?,?)");
				preparedStatement.setString(1, subject.getSubjectId());
				preparedStatement.setString(2, subject.getSubjectName());
				preparedStatement.setString(3, subject.getSemester());
				preparedStatement.setInt(4, subject.getCredits());
				int update = preparedStatement.executeUpdate();
				if (update == 1)
					System.out.println("\n✅ Subject: " + subject.getSubjectName() + " added successfully!");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateSubjectByID(CourseSubjectModel subject, boolean isActive) {
		try {
			connection = Database.getConnection();
			if (checkSubject(subject.getSubjectId())) {
				preparedStatement = connection.prepareStatement(
						"UPDATE subject_table SET semester=?,credits=?,is_active=? WHERE subject_id = ?;");
				preparedStatement.setString(1, subject.getSemester());
				preparedStatement.setInt(2, subject.getCredits());
				preparedStatement.setBoolean(3, isActive);
				preparedStatement.setString(4, subject.getSubjectId());
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println("\n✅ Subject with ID: " + subject.getSubjectId() + " has been Updated.\n");
				return;
			}
			System.out.println("Subject Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean checkSubjectByName(String courseName) {
		connection = Database.getConnection();
		ResultSet receivedId;
		try {
			preparedStatement = connection
					.prepareStatement("SELECT subject_name FROM subject_table WHERE subject_name = ?");
			preparedStatement.setString(1, courseName);
			receivedId = preparedStatement.executeQuery();
			if (!receivedId.next()) {
				System.out.println("Subject Not Found");
				return false;
			}
			receivedId.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void updateSubjectByName(CourseSubjectModel subject, boolean isActive) {
		try {
			connection = Database.getConnection();
			if (checkSubjectByName(subject.getSubjectName())) {
				preparedStatement = connection.prepareStatement(
						"UPDATE subject_table SET semester=?,credits=?,is_active=? WHERE subject_id = ?;");
				preparedStatement.setString(1, subject.getSemester());
				preparedStatement.setInt(2, subject.getCredits());
				preparedStatement.setBoolean(3, isActive);
				preparedStatement.setString(4, subject.getSubjectId());
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println("\n✅ Subject with ID: " + subject.getSubjectId() + " has been Updated.\n");
				return;
			}
			System.out.println("Subject Not Found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void assignSubjectCourse(String subjectId, String courseId) {
		try {
			connection = Database.getConnection();
			if (!CoursesDao.checkCourse(courseId)) {
				System.out.println("Course not found");
				return;
			}
			if (checkSubject(subjectId)) {
				preparedStatement = connection
						.prepareStatement("INSERT INTO course_subject(course_id,subject_id) VALUES(?,?)");
				preparedStatement.setString(1, courseId);
				preparedStatement.setString(2, subjectId);
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println(
							"\n✅ Subject with ID: " + subjectId + " has been assigned course " + courseId + "\n");
				return;
			}
			System.out.println("Subject Not Found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
