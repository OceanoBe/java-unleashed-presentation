package org.oceanobe.utils;

import lombok.experimental.UtilityClass;
import org.oceanobe.model.Student;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@UtilityClass
public class StudentStreamUtils {

    public static void filterAndSortByGrade(List<Student> students, int age, boolean print) {
        System.out.println(">Filter students by age and sort them by grade:\n");
        // Measure memory before operation
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();


        long startTime = System.nanoTime();
        List<Student> filteredAndSortedStudents = students.stream()
                .filter(student -> student.getAge() > age)
                .sorted(Comparator.comparingDouble(Student::getGrade))
                .collect(Collectors.toList());

        // Print the filtered and sorted list
        if (print) {
            filteredAndSortedStudents.forEach(System.out::println);
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        // Measure memory after operation
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        double memoryUsed = (afterMemory - beforeMemory) / 1024.0;
        System.out.println("\n>>> Execution time (Java 8): " + duration + " ms\n\n");
        System.out.println("\n>>>Memory used (Java 8): " + memoryUsed + " KB");

    }

    public void groupByAge(List<Student> students, boolean print) {
        System.out.println(">Group students by age:\n");
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();


        // Group students by age using streams
        Map<Integer, List<Student>> studentsByAge = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));

        if (print) {
            // Print grouped students
            studentsByAge.forEach((age, studentList) -> {
                System.out.println("Age: " + age);
                studentList.forEach(System.out::println);
            });
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        double memoryUsed = (afterMemory - beforeMemory) / 1024.0;

        System.out.println("\n>>> Execution time (Java 8): " + duration + " ms\n\n");
        System.out.println("\n>>>Memory used (Java 8): " + memoryUsed + " KB");
    }

    public static void filterByDate(List<Student> students, LocalDate filterDate) {
        System.out.println("\n>Filter students by enrollment date...");
        List<Student> filteredAndSortedStudents = students.stream()
                .filter(student -> student.getEnrollmentDate().isAfter(filterDate))
                .sorted(Comparator.comparing(Student::getEnrollmentDate))
                .collect(Collectors.toList());

        filteredAndSortedStudents.forEach(System.out::println);
    }

    public static CompletableFuture<List<Student>> filterByEnrollmentDateCompletableFuture(ThreadPoolExecutor executor, List<Student> students, LocalDate enrollmentFilterDate) {

        CompletableFuture<List<Student>> futureStudents = CompletableFuture.supplyAsync(() -> {
            // Simulate a delay
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            return students.stream()
                    .filter(student -> student.getEnrollmentDate().isAfter(enrollmentFilterDate))
                    .collect(Collectors.toList());
        }, executor);

        System.out.println("\n Thread " + Thread.currentThread().getName() + " waiting for completable future to finish processing...");

        // Continue processing once the filtering is complete
        futureStudents.thenAccept(filteredStudents -> {
            System.out.println("\n> Completable Future filtering students on thread " + Thread.currentThread().getName());
            filteredStudents.forEach(System.out::println);
        });
        return futureStudents;
    }

    public static CompletableFuture<List<Student>> filterByAgeCompletableFuture(ThreadPoolExecutor executor, List<Student> students, int age) {

        CompletableFuture<List<Student>> futureStudents = CompletableFuture.supplyAsync(() -> {
            // Simulate a delay
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            return students.stream()
                    .filter(student -> student.getAge() > age)
                    .collect(Collectors.toList());
        }, executor);

        System.out.println("\n Thread " + Thread.currentThread().getName() + " waiting for completable future to finish processing...");

        // Continue processing once the filtering is complete
        futureStudents.thenAccept(filteredStudents -> {
            System.out.println("\n> Completable Future filtering students on thread " + Thread.currentThread().getName());
            filteredStudents.forEach(System.out::println);
        });
        return futureStudents;
    }
}
