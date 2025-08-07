package com.aurionpro.service.dashboardService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.aurionpro.database.Database;

public class DashboardService {

    
    private Connection conn;

    public DashboardService() {
        this.conn = Database.getConnection();
    }

    public void showDashboard() {

        System.out.println("\n==================== STUDENTS DASHBOARD ====================");

        String studentQuery = "SELECT " +
                "ROW_NUMBER() OVER () AS SrNo, " +
                "s.student_id AS StudentID, " +
                "s.student_name AS Name, " +
                "c.course_name AS Course, " +
                "c.course_fees AS TotalFee, " +
                "GROUP_CONCAT(DISTINCT sub.subject_name ORDER BY sub.subject_name SEPARATOR ', ') AS Subjects " +
                "FROM student_table s " +
                "JOIN course_student cs ON s.student_id = cs.student_id " +
                "JOIN course_table c ON cs.course_id = c.course_id " +
                "JOIN course_subject csub ON c.course_id = csub.course_id " +
                "JOIN subject_table sub ON csub.subject_id = sub.subject_id AND sub.is_active = 1 " +
                "WHERE s.is_active = 1 " +
                "GROUP BY s.student_id, s.student_name, c.course_name, c.course_fees " +
                "ORDER BY SrNo;";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(studentQuery)) {

            System.out.printf("\n%-5s %-10s %-22s %-30s %-12s %-40s\n",
                    "SrNo", "StuID", "Name", "Course", "Fee (₹)", "Subjects");
            System.out.println("=".repeat(120));

            while (rs.next()) {
                System.out.printf("%-5d %-10d %-22s %-30s %-12.2f %-40s\n",
                        rs.getInt("SrNo"),
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Course"),
                        rs.getDouble("TotalFee"),
                        rs.getString("Subjects"));
            }

        } catch (Exception e) {
            System.out.println("❌ Error displaying students dashboard: " + e.getMessage());
        }

        System.out.println("\n==================== TEACHERS DASHBOARD ====================");

        String teacherQuery = "SELECT " +
                "ROW_NUMBER() OVER () AS SrNo, " +
                "t.teacher_id AS TeacherID, " +
                "t.name AS Name, " +
                // Latest salary based on max pay_date (subquery)
                "(SELECT tsal.salary_amount FROM teacher_salary tsal " +
                " WHERE tsal.teacher_id = t.teacher_id " +
                " ORDER BY tsal.pay_date DESC LIMIT 1) AS Salary, " +
                "COALESCE(COUNT(DISTINCT ta.attendance_id), 0) AS AttendanceCount, " +
                "ROUND(AVG(DISTINCT tr.rating_value), 2) AS AvgRating, " +
                "GROUP_CONCAT(DISTINCT act.activity_name ORDER BY act.activity_name SEPARATOR ', ') AS Activities " +
                "FROM teacher t " +
                "LEFT JOIN teacher_attendance ta ON ta.teacher_id = t.teacher_id " +
                "LEFT JOIN teacher_rating tr ON tr.teacher_id = t.teacher_id " +
                "LEFT JOIN teacher_activities act ON act.teacher_id = t.teacher_id " +
                "WHERE t.is_active = 1 " +
                "GROUP BY t.teacher_id, t.name " +
                "ORDER BY SrNo;";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(teacherQuery)) {

            System.out.printf("\n%-5s %-10s %-25s %-14s %-15s %-10s %-40s\n",
                    "SrNo", "TeacherID", "Name", "Salary (₹)", "Attendances", "AvgRating", "Activities");
            System.out.println("=".repeat(130));

            while (rs.next()) {
                String activities = rs.getString("Activities");
                if (activities == null || activities.trim().isEmpty()) {
                    activities = "None";
                }
                double salary = rs.getDouble("Salary"); // 0.0 if null
                System.out.printf("%-5d %-10d %-25s %-14.2f %-15d %-10.2f %-40s\n",
                        rs.getInt("SrNo"),
                        rs.getInt("TeacherID"),
                        rs.getString("Name"),
                        salary,
                        rs.getInt("AttendanceCount"),
                        rs.getDouble("AvgRating"),
                        activities);
            }

        } catch (Exception e) {
            System.out.println("❌ Error displaying teachers dashboard: " + e.getMessage());
        }
    }
}
