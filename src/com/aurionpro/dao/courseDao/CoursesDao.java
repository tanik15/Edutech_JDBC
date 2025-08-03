package com.aurionpro.dao.courseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.courseModels.CourseModel;

public class CoursesDao {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;

	private static CourseModel createCourseObj(ResultSet result) {
		CourseModel course = new CourseModel();
		try {
			course.setCourseId(result.getString(1));
			course.setCourseName(result.getString(2));
			course.setCourseDuration(result.getInt(3));
			course.setDescription(result.getString(4));
			course.setCourseFees(result.getInt(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	public static List<CourseModel> getAllCourses() {
		connection = Database.getConnection();
		List<CourseModel> courses = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM course_table WHERE is_active = TRUE");
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					CourseModel course = createCourseObj(result);
					courses.add(course);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courses;
	}

	public static boolean checkCourse(String courseId) {
		connection = Database.getConnection();
		ResultSet receivedId;
		try {
			preparedStatement = connection.prepareStatement("SELECT course_id FROM course_table WHERE student_id = ?");
			preparedStatement.setString(1, courseId);
			receivedId = preparedStatement.executeQuery();
			if (!receivedId.next()) {
				System.out.println("Course not found");
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static CourseModel getACourse(String courseId) {
		connection = Database.getConnection();
		CourseModel course = null;
		if (connection != null && checkCourse(courseId)) {
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM course_table WHERE course_id = ?");
				preparedStatement.setString(1, courseId);
				ResultSet result = preparedStatement.executeQuery();
				result.next();
				course = createCourseObj(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return course;

	}

	public static void deleteCourse(String courseId) {
		try {
			connection = Database.getConnection();
			if (checkCourse(courseId)) {
				preparedStatement = connection
						.prepareStatement("UPDATE course_table SET is_active = FALSE WHERE course_id = ? ");
				preparedStatement.setString(1, courseId);
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println("\n✅ Course with ID: " + courseId + " has been marked as Inactive.\n");
				return;
			}
			System.out.println("Course don't exist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addNewCourse(CourseModel course) {
		connection = Database.getConnection();
		try {
			if (!checkCourse(course.getCourseId())) {
				preparedStatement = connection.prepareStatement(
						"INSERT INTO course_table VALUES (?,?,?,?,?)");
				preparedStatement.setString(1, course.getCourseId());
				preparedStatement.setString(2, course.getCourseName());
				preparedStatement.setInt(3, course.getCourseDuration());
				preparedStatement.setString(4, course.getDescription());
				preparedStatement.setInt(5, course.getCourseFees());
				int update = preparedStatement.executeUpdate();
				if (update == 1)
					System.out.println("\n✅ Course added successfully!");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
