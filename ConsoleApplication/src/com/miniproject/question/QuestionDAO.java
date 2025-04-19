package com.miniproject.question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    public void addQuestion(Question q) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO question (question_text, option1, option2, option3, option4, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, q.getQuestionText());
            stmt.setString(2, q.getOption1());
            stmt.setString(3, q.getOption2());
            stmt.setString(4, q.getOption3());
            stmt.setString(5, q.getOption4());
            stmt.setInt(6, q.getCorrectOption());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding question: " + e.getMessage());
        }
    }

    public List<Question> getAllQuestions() {
        List<Question> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM question";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Question q = new Question();
                q.setQuestionId(rs.getInt("question_id"));
                q.setQuestionText(rs.getString("question_text"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setCorrectOption(rs.getInt("correct_option"));
                list.add(q);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching questions: " + e.getMessage());
        }
        return list;
    }
}

