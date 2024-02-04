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


        assertFalse(scores.findAll().isEmpty(), "점수는 빌 수 없습니다.");
        assertFalse(students.findAll().isEmpty(), "로드 이후 데이터가 빌 수 없습니다.");


        Student sampleStudent = students.findAll().iterator().next();
        assertNotNull(sampleStudent.getScore(), "merge 이후 데이터는 null이 될 수 없습니다");


        Score expectedScoreForSample = scores.findAll().stream()
                .filter(score -> score.getStudentSeq() == sampleStudent.getSeq())
                .findFirst()
                .orElse(null);

        assertNotNull(expectedScoreForSample, "표본 학생의 예상 점수는 null일 수 없습니다.");
        assertEquals(sampleStudent.getScore().getScore(), expectedScoreForSample.getScore(), "학생 표본의 점수는 예상 값과 일치해야 합니다.");
    }



}