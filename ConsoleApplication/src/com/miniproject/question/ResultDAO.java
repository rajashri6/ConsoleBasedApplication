package com.miniproject.question;

import java.sql.*;
import java.util.*;

public class ResultDAO {
    public void saveResult(Result result) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO RESULT (RESULT_ID, STUDENT_ID, SCORE) VALUES (result_seq.NEXTVAL, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, result.getStudentId());
            stmt.setInt(2, result.getScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving result: " + e.getMessage());
        }
    }

    public List<Map<String, Object>> getAllResultsSorted() {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT s.student_id, s.first_name, s.last_name, r.score FROM student s JOIN result r ON s.student_id = r.student_id ORDER BY r.score ASC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", rs.getInt("student_id"));
                map.put("name", rs.getString("first_name") + " " + rs.getString("last_name"));
                map.put("score", rs.getInt("score"));
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching results: " + e.getMessage());
        }
        return list;
    }

    public int getScoreByStudentId(int studentId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT score FROM result WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("score");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching score: " + e.getMessage());
        }
        return -1;
    }
}

