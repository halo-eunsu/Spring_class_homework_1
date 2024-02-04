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
        assertFalse(loadedStudents.isEmpty(), "학생란은 비면 안됩니다.");
    }

    @Test
    void findAll() {

        Collection<Student> allStudents = students.findAll();
        assertTrue(allStudents.size() > 0, "리스트를 반환해야 합니다.");
    }

    @Test
    void merge() {

        Student firstStudentBeforeMerge = students.findAll().iterator().next();
        assertNull(firstStudentBeforeMerge.getScore(), "merge 전 데이터는 null이어야 합니다.");


        students.merge(scores.findAll());


        Student firstStudentAfterMerge = students.findAll().iterator().next();
        assertNotNull(firstStudentAfterMerge.getScore(), "merge 이후 데이터는 null이 될 수 없습니다.");

        assertEquals(firstStudentAfterMerge.getSeq(), firstStudentAfterMerge.getScore().getStudentSeq(), "학생과 점수는 일치해야 합니다.");
    }
}