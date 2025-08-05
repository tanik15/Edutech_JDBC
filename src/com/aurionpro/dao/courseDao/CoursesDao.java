package com.aurionpro.dao.courseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.courseModels.CourseModel;
import com.aurionpro.model.courseModels.CourseSubjectModel;
import com.aurionpro.model.studentsModel.StudentModel;

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

	public static List<CourseModel> getAllCourses(boolean isActive) {
		connection = Database.getConnection();
		List<CourseModel> courses = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM course_table WHERE is_active = ?");
				preparedStatement.setBoolean(1, isActive);
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
			preparedStatement = connection.prepareStatement("SELECT course_id FROM course_table WHERE course_id = ?");
			preparedStatement.setString(1, courseId);
			receivedId = preparedStatement.executeQuery();
			if (!receivedId.next()) {
				
				return false;
			}
			receivedId.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static boolean checkCourseByName(String courseName) {
		connection = Database.getConnection();
		ResultSet receivedId;
		try {
			preparedStatement = connection.prepareStatement("SELECT course_name FROM course_table WHERE course_name = ?");
			preparedStatement.setString(1, courseName);
			receivedId = preparedStatement.executeQuery();
			if (!receivedId.next()) {
				System.out.println("Course Not Found");
				return false;
			}
			receivedId.close();
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
				System.out.println("hii");
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
			System.out.println("Course Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCourseByID(CourseModel course,boolean isActive) {
		try {
			connection = Database.getConnection();
			if (checkCourse(course.getCourseId())) {
				preparedStatement = connection
						.prepareStatement("UPDATE course_table SET course_duration= ?,description= ?, course_fees=? ,is_active=? WHERE course_id = ?;");
				preparedStatement.setInt(1, course.getCourseDuration());
				preparedStatement.setString(2, course.getDescription());
				preparedStatement.setInt(3, course.getCourseFees());
				preparedStatement.setBoolean(4,isActive);
				preparedStatement.setString(5,course.getCourseId());
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println("\n✅ Course with ID: " + course.getCourseId() + " has been Updated.\n");
				return;
			}
			System.out.println("Course Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCourseByName(CourseModel course,boolean isActive) {
		try {
			connection = Database.getConnection();
			if (checkCourseByName(course.getCourseId())) {
				preparedStatement = connection
						.prepareStatement("UPDATE course_table SET course_duration= ?,description= ?, course_fees=? ,is_active=? WHERE course_name = ?;");
				preparedStatement.setInt(1, course.getCourseDuration());
				preparedStatement.setString(2, course.getDescription());
				preparedStatement.setInt(3, course.getCourseFees());
				preparedStatement.setBoolean(4,isActive);
				preparedStatement.setString(5,course.getCourseName());
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println("\n✅ Course with ID: " + course.getCourseName() + " has been marked as Inactive.\n");
				return;
			}
			System.out.println("Course Not Found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void assignCourseStudent(String courseId,int studentId) {
		try {
			connection = Database.getConnection();
			if (checkCourse(courseId)) {
				preparedStatement = connection
						.prepareStatement("INSERT INTO course_student(course_id,student_id) VALUES(?,?)");
				preparedStatement.setString(1, courseId);
				preparedStatement.setInt(2, studentId);
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					System.out.println("\n✅ Student with ID: " + studentId + " has been assigned course "+courseId+"\n");
				return;
			}
			System.out.println("Course Not Found");
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
						"INSERT INTO course_table (course_id,course_name,course_duration,description,course_fees)VALUES (?,?,?,?,?)");
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
	
	public static List<CourseSubjectModel> getSubjectsInCourse(String courseId) {
		List<CourseSubjectModel> subjects = new ArrayList<CourseSubjectModel>();
		try {
			connection = Database.getConnection();
			if (!checkCourse(courseId)) {
				System.out.println("Course not found");
				return subjects;
			}
				preparedStatement = connection
						.prepareStatement("SELECT s.subject_id, s.subject_name,s.semester,s.credits  FROM subject_table s RIGHT JOIN course_subject c ON s.subject_id = c.subject_id where c.course_id=?");
				preparedStatement.setString(1, courseId);
				ResultSet result = preparedStatement.executeQuery();
				while(result.next()) {
					CourseSubjectModel subject = new CourseSubjectModel();
					subject.setSubjectId(result.getString(1));
					subject.setSubjectName(result.getString(2));
					subject.setSemester(result.getString(3));
					subject.setCredits(result.getInt(4));
					subjects.add(subject);
				}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subjects;
	}
	
	public static List<StudentModel> getStudentInCourse(String courseId) {
		List<StudentModel> students = new ArrayList<StudentModel>();
		try {
			connection = Database.getConnection();
			if (!checkCourse(courseId)) {
				System.out.println("Course not found");
				return students;
			}
				preparedStatement = connection
						.prepareStatement("SELECT s.student_id, s.student_name,s.Student_rollnumber FROM student_table s RIGHT JOIN course_student c ON s.student_id = c.student_id where c.course_id=?");
				preparedStatement.setString(1, courseId);
				ResultSet result = preparedStatement.executeQuery();
				while(result.next()) {
					StudentModel student = new StudentModel();
					student.setStudent_id(result.getInt(1));
					student.setStudent_name(result.getString(2));
					student.setStudent_rollnumber(result.getInt(3));
					students.add(student);
				}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}

}
