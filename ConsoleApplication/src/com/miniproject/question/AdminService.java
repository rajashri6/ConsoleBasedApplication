package com.miniproject.question;

import com.miniproject.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminService {
    private ResultDAO resultDAO = new ResultDAO();
    private QuestionDAO questionDAO = new QuestionDAO();

    public void viewAllScoresSorted() {
        List<Map<String, Object>> allResults = resultDAO.getAllResultsSorted();

        System.out.println("\nStudent Scores (ascending order):");
        for (Map<String, Object> entry : allResults) {
            System.out.println("ID: " + entry.get("id") + ", Name: " + entry.get("name") + ", Score: " + entry.get("score"));
        }
    }

    public void getStudentScoreById() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int studentId = sc.nextInt();

        int score = resultDAO.getScoreByStudentId(studentId);
        if (score >= 0) {
            System.out.println("Score for student ID " + studentId + ": " + score);
            System.out.println("Grade: " + (score >= 8 ? "A" : score >= 5 ? "B" : "C"));
        } else {
            System.out.println("No score found for student ID: " + studentId);
        }
    }

    public void addQuestion() {
        Scanner sc = new Scanner(System.in);
        Question q = new Question();

        System.out.print("Enter the question text: ");
        q.setQuestionText(sc.nextLine());

        System.out.print("Option 1: ");
        q.setOption1(sc.nextLine());

        System.out.print("Option 2: ");
        q.setOption2(sc.nextLine());

        System.out.print("Option 3: ");
        q.setOption3(sc.nextLine());

        System.out.print("Option 4: ");
        q.setOption4(sc.nextLine());

        System.out.print("Enter correct option (1-4): ");
        q.setCorrectOption(sc.nextInt());

        questionDAO.addQuestion(q);
        System.out.println("Question added successfully!");
    }
}

