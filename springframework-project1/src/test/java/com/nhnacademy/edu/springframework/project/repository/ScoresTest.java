package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {


        private Scores scores;

        @BeforeEach
        void setUp() {
            scores = CsvScores.getInstance();
            scores.load();
        }

    @Test
    void load() {

        List<Score> loadedScores = scores.findAll();
        assertFalse(loadedScores.isEmpty(), "점수란은 빌 수 없습니다.");
    }

    @Test
    void findAll() {

        List<Score> allScores = scores.findAll();
        assertTrue(allScores.size() > 0, "리스트를 반환해야 합니다.");


        Score firstScore = allScores.get(0);
        assertNotNull(firstScore, "최초 점수는 null이 될 수 없습니다.");

        assertEquals(30, firstScore.getScore(), "배치가 정확하지 않습니다.");
    }
}