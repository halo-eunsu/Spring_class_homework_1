package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    private Students students;
    private Scores scores;

    @BeforeEach
    void setUp() {
        students = CsvStudents.getInstance();
        students.load();

        scores = CsvScores.getInstance();
        scores.load();
    }

    @Test
    void load() {

        Collection<Student> loadedStudents = students.findAll();
        assertFalse(loadedStudents.isEmpty(), "Students should not be empty after loading data");
    }

    @Test
    void findAll() {

        Collection<Student> allStudents = students.findAll();
        assertTrue(allStudents.size() > 0, "findAll should return a list with elements");
    }

    @Test
    void merge() {

        Student firstStudentBeforeMerge = students.findAll().iterator().next();
        assertNull(firstStudentBeforeMerge.getScore(), "Student score should be null before merge");


        students.merge(scores.findAll());


        Student firstStudentAfterMerge = students.findAll().iterator().next();
        assertNotNull(firstStudentAfterMerge.getScore(), "Student score should not be null after merge");

        assertEquals(firstStudentAfterMerge.getSeq(), firstStudentAfterMerge.getScore().getStudentSeq(), "Student sequence should match score sequence");
    }
}
