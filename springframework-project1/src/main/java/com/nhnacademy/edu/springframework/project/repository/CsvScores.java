package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    private List<Score> scoreList = new ArrayList<>();
    private CsvScores(){}

    private static CsvScores instance = null;

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/


    public static Scores getInstance() {
        if (instance == null) {
            instance = new CsvScores();
        }
        return instance;
    }




    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data/score.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    int studentSeq = Integer.parseInt(data[0].trim());
                    int score = Integer.parseInt(data[1].trim());
                    scoreList.add(new Score(studentSeq, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
