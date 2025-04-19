package com.miniproject.student;


import com.miniproject.student.*;

import java.util.Scanner;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    public void registerStudent() {
        Scanner sc = new Scanner(System.in);
        Student student = new Student();

        System.out.print("Enter First Name: ");
        student.setFirstName(sc.nextLine());

        System.out.print("Enter Last Name: ");
        student.setLastName(sc.nextLine());

        System.out.print("Enter Username: ");
        student.setUsername(sc.nextLine());

        System.out.print("Enter Password: ");
        student.setPassword(sc.nextLine());

        System.out.print("Enter City: ");
        student.setCity(sc.nextLine());

        System.out.print("Enter Email: ");
        student.setEmail(sc.nextLine());

        System.out.print("Enter Mobile Number: ");
        student.setMobile(sc.nextLine());

        if (studentDAO.registerStudent(student)) {
            System.out.println("Registration Successful!");
        } else {
            System.out.println("Registration Failed.");
        }
    }

    public Student loginStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Student loggedInStudent = studentDAO.login(username, password);

        if (loggedInStudent != null) {
            System.out.println("Login successful. Welcome, " + loggedInStudent.getFirstName());
        } else {
            System.out.println("Invalid credentials. Try again.");
        }

        return loggedInStudent;
    }
}

