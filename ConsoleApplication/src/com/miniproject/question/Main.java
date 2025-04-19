package com.miniproject.question;


import com.miniproject.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        QuizService quizService = new QuizService();
        AdminService adminService = new AdminService();

        Student loggedInStudent = null;

        while (true) {
            System.out.println("\n--- Welcome to Quiz Application ---");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3.Take Quize");
            System.out.println("4. Display Quiz result");
            System.out.println("5. Admin: Display all students score (ascending)");
            System.out.println("6. Admin: Fetch student score by ID");
            System.out.println("7. Admin: Add a question to DB");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    studentService.registerStudent();
                    break;
                case 2:
                    loggedInStudent = studentService.loginStudent();
                    break;
                case 3:
                    if (loggedInStudent != null)
                        quizService.takeQuiz(loggedInStudent);
                    else
                        System.out.println("Please login first.");
                    break;
                case 4:
                    if (loggedInStudent != null)
                        quizService.viewResult(loggedInStudent);
                    else
                        System.out.println(" Please login first.");
                    break;
                case 5:
                    adminService.viewAllScoresSorted();
                    break;
                case 6:
                    adminService.getStudentScoreById();
                    break;
                case 7:
                    adminService.addQuestion();
                    break;
                case 0:
                    System.out.println(" Exiting the application.");
                    return;
                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }
}
