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
            assertFalse(loadedScores.isEmpty(), "Scores should not be empty after loading data");
        }

        @Test
        void findAll() {

            List<Score> allScores = scores.findAll();
            assertTrue(allScores.size() > 0, "findAll should return a list with elements");


            Score firstScore = allScores.get(0);
            assertNotNull(firstScore, "The first score should not be null");

            assertEquals(30, firstScore.getScore(), "The score of the first student should match the expected value");
        }
}
