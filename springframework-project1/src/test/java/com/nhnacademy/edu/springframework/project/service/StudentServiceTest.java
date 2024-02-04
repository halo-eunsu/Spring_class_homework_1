package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.junit.jupiter.api.Test;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;



import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {



    private StudentService studentService;

    @BeforeEach
    void setUp() {

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();


        studentService = new DefaultStudentService();
    }

    @Test
    void getPassedStudents() {

        Collection<Student> passedStudents = studentService.getPassedStudents();


        assertFalse(passedStudents.isEmpty(), "합격한 학생이 있어야 합니다.");


        passedStudents.forEach(student -> assertTrue(student.getScore().getScore() >= 60, "합격한 학생의 점수는 60점 이상이어야 합니다."));
    }

    @Test
    void getStudentsOrderByScore() {

        Collection<Student> studentsOrderedByScore = studentService.getStudentsOrderByScore();


        assertFalse(studentsOrderedByScore.isEmpty(), "점수순으로 정렬된 학생이 있어야 합니다.");


        Student previousStudent = null;
        for (Student student : studentsOrderedByScore) {
            if (previousStudent != null) {
                assertTrue(previousStudent.getScore().getScore() <= student.getScore().getScore(), "학생들은 점수에 따라 오름차순으로 정렬되어야 합니다.");
            }
            previousStudent = student;
        }
    }



}