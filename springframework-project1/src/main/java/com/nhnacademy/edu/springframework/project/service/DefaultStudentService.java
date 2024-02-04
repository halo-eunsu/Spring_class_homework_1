package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@Component
public class DefaultStudentService implements StudentService {
    @Override
    public Collection<Student> getPassedStudents() {
        Students studentRepository = CsvStudents.getInstance();

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getScore() != null && !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = CsvStudents.getInstance();

        List<Student> sortedStudents = studentRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(student -> student.getScore().getScore()))
                .collect(Collectors.toList());

        return sortedStudents;
    }

}
