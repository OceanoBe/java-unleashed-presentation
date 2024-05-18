package org.oceanobe.utils;

import lombok.experimental.UtilityClass;
import org.oceanobe.model.Student;

import java.time.LocalDate;
import java.util.*;

@UtilityClass
public final class StudentUtils {

    public static void filterAndSortByGrade(List<Student> students, int age, boolean print) {
        System.out.println("Filter students by age and sort them by grade...");
        List<Student> filteredStudents = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Suggest garbage collection
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

        long startTime = System.nanoTime();

        for (Student student : students) {
            if (student.getAge() > age) {
                filteredStudents.add(student);
            }
        }

        Collections.sort(filteredStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.getGrade(), s2.getGrade());
            }
        });

        if(print) {
            for (Student student : filteredStudents) {
                System.out.println(student);
            }
        }
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        // Measure memory after operation
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        double memoryUsed = (afterMemory - beforeMemory) / 1024.0;

        System.out.println("\n>>> Execution time (Pre-Java 8): " + duration + " ms");
        System.out.println("Memory used (Pre-Java 8): " + memoryUsed + " KB");
    }

    public void groupByAge(List<Student> students, boolean print) {
        System.out.println("Group students by age...");
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Suggest garbage collection
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

        long startTime = System.nanoTime();
        Map<Integer, List<Student>> studentsByAge = new HashMap<>();

        for (Student student : students) {
            int age = student.getAge();
            if (!studentsByAge.containsKey(age)) {
                studentsByAge.put(age, new ArrayList<>());
            }
            studentsByAge.get(age).add(student);
        }

        if(print) {
            for (Map.Entry<Integer, List<Student>> entry : studentsByAge.entrySet()) {
                System.out.println("Age: " + entry.getKey());
                for (Student student : entry.getValue()) {
                    System.out.println(student);
                }
            }
        }
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        // Measure memory after operation
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        double memoryUsed = (afterMemory - beforeMemory) / 1024.0;
        System.out.println("\n>>>Execution time (Pre-Java 8): " + duration + " ms");
        System.out.println("Memory used (Pre-Java 8): " + memoryUsed + " KB");
    }

    public static void filterByDate(List<Student> students, Date filterDate) {
        System.out.println("\n>Filter students by graduation date...");
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getGraduationDate().before(filterDate)) {
                filteredStudents.add(student);
            }
        }

        Collections.sort(filteredStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getGraduationDate().compareTo(s2.getGraduationDate());
            }
        });

        for (Student student : filteredStudents) {
            System.out.println(student);
        }

    }
}
