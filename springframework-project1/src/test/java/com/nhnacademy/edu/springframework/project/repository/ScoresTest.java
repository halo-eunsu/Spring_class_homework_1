package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {


        private Scores scores;

        @BeforeEach
        void setUp() {
            scores = CsvScores.getInstance(); // CsvScores의 인스턴스를 가져옵니다.
            scores.load(); // 테스트 전에 데이터를 로드합니다.
        }

        @Test
        void load() {
            // 데이터 로드 후, findAll()이 비어있지 않은 리스트를 반환하는지 검증합니다.
            List<Score> loadedScores = scores.findAll();
            assertFalse(loadedScores.isEmpty(), "Scores should not be empty after loading data");
        }

        @Test
        void findAll() {
            // findAll() 메서드가 예상하는 크기의 리스트를 반환하는지 검증합니다.
            // 이 부분은 실제 score.csv 파일의 데이터 양에 따라 달라질 수 있으므로, 적절히 조정이 필요합니다.
            List<Score> allScores = scores.findAll();
            assertTrue(allScores.size() > 0, "findAll should return a list with elements");

            // 선택적으로, 반환된 Score 객체의 일부 속성을 검증하여 올바르게 로드되었는지 확인할 수 있습니다.
            // 예를 들어, 첫 번째 Score 객체의 점수가 예상 값과 일치하는지 확인합니다.
            // 이는 실제 score.csv 파일의 첫 번째 라인의 데이터에 따라 달라집니다.
            Score firstScore = allScores.get(0);
            assertNotNull(firstScore, "The first score should not be null");
            // 예를 들어 첫 번째 학생의 점수가 30이라면, 아래와 같이 검증합니다.
            assertEquals(30, firstScore.getScore(), "The score of the first student should match the expected value");
        }
}
