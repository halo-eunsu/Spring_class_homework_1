package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {

        Students studentRepository = CsvStudents.getInstance();
        Scores scoresRepository = CsvScores.getInstance();


        List<Student> students = studentRepository.findAll()
                .stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());

        List<Score> result = new ArrayList<>();


        for (Student student : students) {
            int studentSeq = student.getSeq();
            List<Score> studentScores = scoresRepository.findAll()
                    .stream()
                    .filter(score -> score.getStudentSeq() == studentSeq)
                    .collect(Collectors.toList());
            result.addAll(studentScores);
        }

        return result;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {


        Scores scoresRepository = CsvScores.getInstance();

        return scoresRepository.findAll()
                .stream()
                .filter(score -> score.getStudentSeq() == seq)
                .findFirst()
                .orElse(null);

    }
}
