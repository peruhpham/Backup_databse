package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseOperations {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/StudentEvaluation?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "<mật khẩu>";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    public static void addStudent(String id, String name) {
        String query = "INSERT INTO STUDENTS (Student_id, Full_name, Bdate, Gender, Email, Phone) " +
                "VALUES (?, ?, '2000-01-01', 'Male', 'default@example.com', '0000000000')";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
            Logger.log("Added student: " + name);
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            Logger.log("Error adding student: " + e.getMessage());
        }
    }

    public static void deleteStudent(String id) {
        String query = "DELETE FROM STUDENTS WHERE Student_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully.");
                Logger.log("Deleted student with ID: " + id);
            } else {
                System.out.println("No student found with ID: " + id);
                Logger.log("Failed to delete student. No student found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            Logger.log("Error deleting student: " + e.getMessage());
        }
    }
}
