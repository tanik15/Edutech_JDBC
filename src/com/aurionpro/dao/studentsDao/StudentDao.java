package com.aurionpro.dao.studentsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.studentsModel.StudentCourseModel;
import com.aurionpro.model.studentsModel.StudentFeesModel;
import com.aurionpro.model.studentsModel.StudentModel;

public class StudentDao {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;

	private static StudentModel createStudentObj(ResultSet result) {
		StudentModel student = new StudentModel();
		try {
			student.setStudent_id(result.getInt(1));
			student.setStudent_name(result.getString(2));
			student.setStudent_rollnumber(result.getInt(3));
			student.setStudent_DOB(result.getString(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	public static List<StudentModel> getAllStudents() {
		connection = Database.getConnection();
		List<StudentModel> students = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM Student_table WHERE is_active = TRUE");
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
//				System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getInt(3));
					StudentModel student = createStudentObj(result);
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
		connection = Database.getConnection();
		ResultSet receivedId;
		try {
			preparedStatement = connection
					.prepareStatement("SELECT student_id FROM Student_table WHERE student_id = ?");
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

	public static StudentModel getAStudent(int studentId) {
		connection = Database.getConnection();
		StudentModel student = null;
		if (connection != null && checkStudent(studentId)) {
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM Student_table WHERE student_id = ?");
				preparedStatement.setInt(1, studentId);
				ResultSet result = preparedStatement.executeQuery();
				result.next();
				student = createStudentObj(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return student;

	}

	public static void deleteStudent(int id) {
		try {
			connection = Database.getConnection();
			if (checkStudent(id)) {
				preparedStatement = connection
						.prepareStatement("UPDATE Student_table SET is_active = FALSE WHERE student_id = ? ");
				preparedStatement.setDouble(1, id);
				int update = preparedStatement.executeUpdate();
				System.out.println("\n✅ Student with ID: " + id + " has been marked as Inactive.\n");
				return;
			}
			System.out.println("Student don't exist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addNewStudent(StudentModel student) {
		connection = Database.getConnection();
		try {
			int rollnumber = 0;
			preparedStatement = connection.prepareStatement("SELECT MAX(Student_rollnumber) from student_table");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				rollnumber = rs.getInt(1);
			}
			rs.close();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO student_table(student_name,Student_rollnumber,student_DOB) VALUES (?,?,?)");
			preparedStatement.setString(1, student.getStudent_name());
			preparedStatement.setInt(2, rollnumber + 1);
			preparedStatement.setString(3, student.getStudent_DOB());
			int update = preparedStatement.executeUpdate();
			if (update == 1)
				System.out.println("\n✅ Student added successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<StudentCourseModel> getStudentCourses() {
		List<StudentCourseModel> studentsCourses = new ArrayList<>();
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT s.Student_rollnumber,s.student_name, group_concat(cc.course_name), sum(cc.course_fees) FROM student_table s LEFT JOIN course_student c ON c.student_id = s.student_id LEFT JOIN course_table cc ON cc.course_id = c.course_id GROUP BY   s.student_id, s.student_name, s.student_rollnumber");
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				StudentCourseModel studentCourses = new StudentCourseModel();
				studentCourses.setstudentRollnumber(result.getInt(1));
				studentCourses.setStudentName(result.getString(2));
				studentCourses.setStudentCourses(result.getString(3));
				studentCourses.setStudentFees(result.getInt(4));
				studentsCourses.add(studentCourses);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentsCourses;
	}
	public static List<StudentCourseModel> getStudentSubjects() {
		List<StudentCourseModel> studentsCourses = new ArrayList<>();
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT s.Student_rollnumber,s.student_name, group_concat(st.subject_name) FROM student_table s LEFT JOIN course_student c ON c.student_id = s.student_id LEFT JOIN course_subject cs ON cs.course_id = c.course_id LEFT JOIN subject_table st ON st.subject_id = cs.subject_id WHERE cc.is_active=TRUE GROUP BY s.student_id, s.student_name, s.student_rollnumber");
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				StudentCourseModel studentCourses = new StudentCourseModel();
				studentCourses.setstudentRollnumber(result.getInt(1));
				studentCourses.setStudentName(result.getString(2));
				studentCourses.setStudentSubjects(result.getString(3));
				studentsCourses.add(studentCourses);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentsCourses;
	}
	

}
