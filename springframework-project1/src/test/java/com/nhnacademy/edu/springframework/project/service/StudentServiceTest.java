package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.junit.jupiter.api.Test;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;



import static org.junit.jupiter.api.Assertions.*;


@Component
class StudentServiceTest {



    @Autowired
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


        assertFalse(passedStudents.isEmpty(), "There should be passed students");


        passedStudents.forEach(student -> assertTrue(student.getScore().getScore() >= 60, "Passed student should have score >= 60"));
    }

    @Test
    void getStudentsOrderByScore() {

        Collection<Student> studentsOrderedByScore = studentService.getStudentsOrderByScore();


        assertFalse(studentsOrderedByScore.isEmpty(), "There should be students ordered by score");


        Student previousStudent = null;
        for (Student student : studentsOrderedByScore) {
            if (previousStudent != null) {
                assertTrue(previousStudent.getScore().getScore() <= student.getScore().getScore(), "Students should be ordered by score ascendingly");
            }
            previousStudent = student;
        }
    }



}