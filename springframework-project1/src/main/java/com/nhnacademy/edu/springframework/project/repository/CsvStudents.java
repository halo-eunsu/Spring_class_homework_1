package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CsvStudents implements Students {





    private static CsvStudents instance = null;
    private List<Student> students = new ArrayList<>();

    public CsvStudents(){

    }
    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        if (instance == null) {
            instance = new CsvStudents();
        }
        return instance;
    }


    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data/student.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    int studentSeq = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    students.add(new Student(studentSeq, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Student> findAll() {

        return new ArrayList<>(students);
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for (Student student : students) {
            int studentSeq = student.getSeq();
            List<Score> studentScores = scores.stream()
                    .filter(score -> score.getStudentSeq() == studentSeq)
                    .collect(Collectors.toList());
            student.setScore(studentScores.isEmpty() ? null : studentScores.get(0));
        }
    }
}
