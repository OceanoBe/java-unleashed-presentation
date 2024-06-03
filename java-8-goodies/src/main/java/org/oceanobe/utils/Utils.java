package org.oceanobe.utils;

import lombok.experimental.UtilityClass;

import org.oceanobe.model.Student;

import java.time.LocalDate;
import java.util.*;

@UtilityClass
public class Utils {

    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String name = UUID.randomUUID().toString();
            int age = 16 + random.nextInt(10); // Random age between 16 and 25
            double grade = 60.0 + (random.nextDouble() * 40.0); // Random grade between 60.0 and 100.0
            LocalDate enrollmentDate = LocalDate.now();
            students.add(new Student(name, age, grade, enrollmentDate, new Date(124, 5, 20)));
        }
        return students;
    }
}
