package org.oceanobe;

import org.oceanobe.flow.BatchStudentSubscriber;
import org.oceanobe.flow.StudentPublisher;
import org.oceanobe.flow.StudentSubscriber;
import org.oceanobe.model.Student;
import org.oceanobe.process.api.ProcessApi;
import org.oceanobe.process.impl.ProcessApiImpl;
import org.oceanobe.utils.FileUtils;
import org.oceanobe.utils.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Java11Goodies {

    private static final Logger logger = Logger.getLogger(Java11Goodies.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        logger.info("Hello there!");

        //printProcessData();

        publishListOfStudents();
        Thread.sleep(2000);

        //Java 11 String operations
        //printStrings();

        //Java 11 File Operations
        //exampleFileWriting();
        //exampleFileRead();


    }

    private static void exampleFileWriting() {
        try {
            Path path = Paths.get("example.txt");
            String content = "Hello, World!";

            FileUtils.writeToFile(path, content);
            System.out.println("File written successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exampleFileRead() {
        try {
            Path path = Paths.get("example.txt");
            String content = FileUtils.readFile(path, StandardCharsets.UTF_8);
            System.out.println("File content: " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("File read successfully");
    }

    private static void printStrings() {
        String s = "";
        System.out.println("Blank check: " + StringUtils.isBlank(s));

        s = " Java presentation ";
        System.out.println(StringUtils.repeat(s, 3));

        System.out.println(StringUtils.strip(s));
        System.out.println(StringUtils.stripLeading(s));
        System.out.println(StringUtils.stripTrailing(s));

        s = "First line\nSecond line\nThird line";
        StringUtils.lines(s).forEach(System.out::println);
    }

    private static void publishListOfStudents() {
        List<Student> students = Arrays.asList(
                new Student("John", 18, 85.5, LocalDate.of(2019, 1, 15), new Date(123, 0, 15)),
                new Student("Jane", 20, 92.3, LocalDate.of(2018, 6, 10), new Date(122, 5, 10)),
                new Student("Tom", 17, 70.4, LocalDate.of(2020, 9, 25), new Date(124, 8, 25)),
                new Student("Lucy", 22, 88.9, LocalDate.of(2017, 4, 30), new Date(121, 3, 30))
        );
        // Create publisher and subscribers
        StudentPublisher publisher = new StudentPublisher();
        StudentSubscriber subscriber = new StudentSubscriber();
        BatchStudentSubscriber batchStudentSubscriber = new BatchStudentSubscriber(3);

        // Subscribe the subscribers to the publisher
        publisher.subscribe(subscriber);
        publisher.subscribe(batchStudentSubscriber);

        // Publish the students
        publisher.publishStudents(students);
    }

    private static void printProcessData() throws IOException {
        ProcessApi processApi = new ProcessApiImpl();
        processApi.printProcessInfo();
        processApi.printChildProcessInfo();
    }
}