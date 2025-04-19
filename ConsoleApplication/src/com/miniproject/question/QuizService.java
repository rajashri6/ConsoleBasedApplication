package com.miniproject.question;

import com.miniproject.*;

import java.util.List;
import java.util.Scanner;

public class QuizService {
    private QuestionDAO questionDAO = new QuestionDAO();
    private ResultDAO resultDAO = new ResultDAO();

    public void takeQuiz(Student loggedInStudent) {
        Scanner sc = new Scanner(System.in);
        List<Question> quizQuestions = questionDAO.getAllQuestions();
        int score = 0;

        System.out.println("\n--- Start Quiz ---");
        for (Question q : quizQuestions) {
            System.out.println("\nQ: " + q.getQuestionText());
            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.println("4. " + q.getOption4());

            // Collect the answer
            System.out.print("Enter your answer (1-4): ");
            int answer = sc.nextInt();

            // Check if the answer is correct
            if (answer == q.getCorrectOption()) {
                score++;
            }
        }

        // Store the result
        Result result = new Result();
        result.setStudentId(loggedInStudent.getStudentId());
        result.setScore(score);
        resultDAO.saveResult(result);

        System.out.println("\nQuiz completed!");
    }

//    public void viewResult(Student loggedInStudent) {
//    	 int score = resultDAO.getScoreByStudentId(loggedInStudent.getStudentId());
//    	 System.out.println("Grade: " + (score >= 8 ? "A" : score >= 5 ? "B" : "C"));    
//        System.out.println("\nYour score: " + score);
//    }
    
    public void viewResult(Student loggedInStudent) {
        int score = resultDAO.getScoreByStudentId(loggedInStudent.getStudentId());

        if (score == -1) {
            System.out.println("No result found. Please take the quiz first.");
            return;
        }

        String grade = (score >= 8) ? "A" : (score >= 5) ? "B" : "C";

        System.out.println("Your Score: " + score + "/10");
        System.out.println("Your Grade: " + grade);
    }

    
}
