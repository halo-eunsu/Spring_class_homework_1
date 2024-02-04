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


        assertFalse(scores.isEmpty(), "학생 이름과 대응하는 점수는 비워둘 수 없습니다: " + studentName);





        assertTrue(scores.stream().anyMatch(score -> score.getScore() == 30), "학생 이름에 대한 예상 점수를 찾을 수 없습니다.: " + studentName);
    }

    @Test
    void getScoreByStudentSeq() {

        int studentSeq = 1;
        Score score = gradeQueryService.getScoreByStudentSeq(studentSeq);


        assertNotNull(score, "학생의 점수는 null이 될 수 없습니다.: " + studentSeq);


        assertEquals(30, score.getScore(), "점수가 학생의 예상 값과 일치하지 않습니다.: " + studentSeq);
    }
}