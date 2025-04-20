package com.example.libraryapp;

import java.util.HashMap;

public class StudentData {
    public static HashMap<String, String> getStudentInfo(String studentId) {
        HashMap<String, String> student = new HashMap<>();
        if(studentId.equals("101")) {
            student.put("Name", "Alice");
            student.put("Email", "alice@example.com");
        } else {
            student.put("Name", "Unknown");
            student.put("Email", "unknown@example.com");
        }
        return student;
    }
}