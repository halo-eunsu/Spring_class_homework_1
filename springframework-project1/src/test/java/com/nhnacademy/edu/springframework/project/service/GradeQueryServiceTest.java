package com.nhnacademy.edu.springframework.project.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;



class GradeQueryServiceTest {
    private GradeQueryService gradeQueryService;

    @BeforeEach
    void setUp() {

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();


        gradeQueryService = new DefaultGradeQueryService();
    }

    @Test
    void getScoreByStudentName() {

        String studentName = "A";
        List<Score> scores = gradeQueryService.getScoreByStudentName(studentName);


        assertFalse(scores.isEmpty(), "Scores should not be empty for student name: " + studentName);





        assertTrue(scores.stream().anyMatch(score -> score.getScore() == 30), "Expected score not found for student name: " + studentName);
    }

    @Test
    void getScoreByStudentSeq() {

        int studentSeq = 1; // 예시 시퀀스 번호, 실제 데이터에 맞게 조정 필요
        Score score = gradeQueryService.getScoreByStudentSeq(studentSeq);


        assertNotNull(score, "Score should not be null for student sequence: " + studentSeq);


        assertEquals(30, score.getScore(), "Score does not match expected value for student sequence: " + studentSeq);
    }
}