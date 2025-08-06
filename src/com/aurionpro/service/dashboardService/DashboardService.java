package com.aurionpro.service.dashboardService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.aurionpro.database.DBUtil;


public class DashboardService {
	public void showDashboard() {
		String query = "SELECT " +
				"ROW_NUMBER() OVER () AS `Sr. No.`, " +
				"s.student_id AS `StudentID`, " +
				"s.student_name AS `Name`, " +
				"c.course_name AS `Course`, " +
				"c.course_fees AS `Total Fee`, " +
				"c.course_fees AS `Paid Fee`, " +
				"0 AS `Pending Fee`, " +
				"GROUP_CONCAT(DISTINCT sub.subject_name SEPARATOR ', ') AS `Subjects`, " +
				"GROUP_CONCAT(DISTINCT t.name SEPARATOR ', ') AS `Teachers`, " +
				"COALESCE(SUM(DISTINCT tsal.salary_amount), 0) AS `Total Salary Paid`, " +
				"COALESCE(COUNT(DISTINCT ta.attendance_id), 0) AS `Total Attendances`, " +
				"ROUND(AVG(DISTINCT tr.rating_value), 2) AS `Avg Rating`, " +
				"GROUP_CONCAT(DISTINCT act.activity_name SEPARATOR ', ') AS `Activities` " +
				"FROM student_table s " +
				"JOIN course_student cs ON s.student_id = cs.student_id " +
				"JOIN course_table c ON cs.course_id = c.course_id " +
				"JOIN course_subject csub ON c.course_id = csub.course_id " +
				"JOIN subject_table sub ON csub.subject_id = sub.subject_id AND sub.is_active = 1 " +
				"LEFT JOIN teacher_subject ts ON ts.subject_id = sub.subject_id " +
				"LEFT JOIN teacher t ON t.teacher_id = ts.teacher_id AND t.is_active = 1 " +
				"LEFT JOIN teacher_salary tsal ON tsal.teacher_id = t.teacher_id " +
				"LEFT JOIN teacher_attendance ta ON ta.teacher_id = t.teacher_id " +
				"LEFT JOIN teacher_rating tr ON tr.teacher_id = t.teacher_id " +
				"LEFT JOIN teacher_activities act ON act.teacher_id = t.teacher_id " +
				"WHERE s.is_active = 1 " +
				"GROUP BY s.student_id, s.student_name, c.course_name, c.course_fees";

		try (Connection conn = DBUtil.getConnection();  // ✅ use your DBUtil
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {

			System.out.printf("\n%-5s %-10s %-20s %-25s %-10s %-10s %-10s %-30s %-30s %-18s %-17s %-12s %-30s\n",
					"SrNo", "StuID", "Name", "Course", "TotalFee", "PaidFee", "Pending",
					"Subjects", "Teachers", "SalaryPaid", "Attendances", "AvgRating", "Activities");
			System.out.println("=".repeat(220));

			while (rs.next()) {
				System.out.printf("%-5d %-10d %-20s %-25s %-10.2f %-10.2f %-10.2f %-30s %-30s %-18.2f %-17d %-12.2f %-30s\n",
						rs.getInt("Sr. No."),
						rs.getInt("StudentID"),
						rs.getString("Name"),
						rs.getString("Course"),
						rs.getDouble("Total Fee"),
						rs.getDouble("Paid Fee"),
						rs.getDouble("Pending Fee"),
						rs.getString("Subjects"),
						rs.getString("Teachers"),
						rs.getDouble("Total Salary Paid"),
						rs.getInt("Total Attendances"),
						rs.getDouble("Avg Rating"),
						rs.getString("Activities")
				);
			}

		} catch (Exception e) {
			System.out.println("❌ Error displaying dashboard: " + e.getMessage());
		}
	}
}