package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {



    private DataLoadService dataLoadService;

    @BeforeEach
    void setUp() {

        dataLoadService = new CsvDataLoadService();
    }

    @Test
    void loadAndMerge() {

        dataLoadService.loadAndMerge();


        Scores scores = CsvScores.getInstance();
        Students students = CsvStudents.getInstance();


        assertFalse(scores.findAll().isEmpty(), "Scores should not be empty after loadAndMerge");
        assertFalse(students.findAll().isEmpty(), "Students should not be empty after loadAndMerge");


        Student sampleStudent = students.findAll().iterator().next();
        assertNotNull(sampleStudent.getScore(), "Student score should not be null after merge");


        Score expectedScoreForSample = scores.findAll().stream()
                .filter(score -> score.getStudentSeq() == sampleStudent.getSeq())
                .findFirst()
                .orElse(null);

        assertNotNull(expectedScoreForSample, "Expected score for sample student should not be null");
        assertEquals(sampleStudent.getScore().getScore(), expectedScoreForSample.getScore(), "Sample student's score should match the expected value");
    }



}