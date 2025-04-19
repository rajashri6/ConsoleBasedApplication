package com.miniproject.student;

import java.sql.*;

public class StudentDAO {
    public boolean registerStudent(Student student) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO student (first_name, last_name, username, password, city, email, mobile) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getUsername());
            stmt.setString(4, student.getPassword());
            stmt.setString(5, student.getCity());
            stmt.setString(6, student.getEmail());
            stmt.setString(7, student.getMobile());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error registering student: " + e.getMessage());
        }
        return false;
    }

    public Student login(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM student WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }
}

