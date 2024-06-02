package org.oceanobe;

import org.oceanobe.model.Student;
import org.oceanobe.multithreading.CustomThreadPool;
import org.oceanobe.utils.StudentStreamUtils;
import org.oceanobe.utils.StudentUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class Java8Goodies {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student("John", 18, 85.5, LocalDate.of(2019, 1, 15), new Date(123, 0, 15)),
                new Student("Jane", 20, 92.3, LocalDate.of(2018, 6, 10), new Date(122, 5, 10)),
                new Student("Tom", 17, 70.4, LocalDate.of(2020, 9, 25), new Date(124, 8, 25)),
                new Student("Lucy", 22, 88.9, LocalDate.of(2017, 4, 30), new Date(121, 3, 30))
        );

        LocalDate enrollmentFilterDate = LocalDate.of(2019, 1, 1);
        Date graduationFilterDate = new Date(122, 0, 1); // January 1, 2019
        System.out.println("==========Before Java 8==========\n");
        StudentUtils.filterAndSortByGrade(students, 18, true);
        StudentUtils.groupByAge(students, true);

        StudentUtils.filterByDate(students, graduationFilterDate);

        System.out.println("\n==========After Java 8==========\n");
        StudentStreamUtils.filterAndSortByGrade(students, 18, true);
        StudentStreamUtils.groupByAge(students, true);

        StudentStreamUtils.filterByDate(students, enrollmentFilterDate);

        // Filter students enrolled after January 1, 2019

//        List<Student> bigData = Utils.generateStudents(1000000);
//
//        System.out.println("==========Before Java 8==========\n");
//        StudentUtils.filterAndSortByGrade(bigData, 18, false);
//        StudentUtils.groupByAge(bigData, false);
//
//        System.out.println("\n==========After Java 8==========\n");
//        StudentStreamUtils.filterAndSortByGrade(bigData, 18, false);
//        StudentStreamUtils.groupByAge(bigData, false);

        // Create a custom ThreadPoolExecutor
        ThreadPoolExecutor executor = CustomThreadPool.createCustomThreadPool(4, 8, 0L, "student-async");

        CompletableFuture<List<Student>> studentsFilteredByDate = StudentStreamUtils.filterByEnrollmentDateCompletableFuture(executor, students, enrollmentFilterDate);
        CompletableFuture<List<Student>> studentsFilteredByAge = StudentStreamUtils.filterByAgeCompletableFuture(executor, students, 18);
        // Keep the main thread alive to see the results
        try {
            studentsFilteredByDate.get(); // This will block until the CompletableFuture is complete
            studentsFilteredByAge.get(); // This will block until the CompletableFuture is complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


    }


}