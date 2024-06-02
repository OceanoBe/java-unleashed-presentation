package org.oceanobe;

import org.oceanobe.model.*;
import org.oceanobe.utils.NumberFormattingUtils;
import org.oceanobe.utils.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Java17Goodies {

    public static void main(String[] args) {

        //switchExamples();
        //numberFormatExamples();
        //stringExamples();
        sealedClassesExamples();
    }

    private static void sealedClassesExamples() {
        List<Person> persons = Arrays.asList(
                new Student("John", 18, 85.5, LocalDate.of(2019, 1, 15), new Date(123, 0, 15)),
                new Student("Jane", 20, 92.3, LocalDate.of(2018, 6, 10), new Date(122, 5, 10)),
                new Student("Tom", 17, 70.4, LocalDate.of(2020, 9, 25), new Date(124, 8, 25)),
                new Student("Lucy", 22, 88.9, LocalDate.of(2017, 4, 30), new Date(121, 3, 30)),
                        new Teacher("Walter",54,"Chemistry",LocalDate.of(2002, 1, 30),7000.00),
                        new Teacher("Anna",32,"Physics",LocalDate.of(2022, 1, 30),3000.00),
                        new Teacher("Clyde",40,"Math",LocalDate.of(2012, 1, 30),5000.00));

        Dog dog = new Dog("Spot", 2, LocalDate.of(2022, 4,30));

        List<Animal> group = new ArrayList<>();
        group.addAll(persons);
        group.add(dog);

        group.forEach(Java17Goodies::performAction);


    }

    private static void performAction(Animal animal) {

        if(animal instanceof Person person) {
            person.talk();
            if(person instanceof Student s) {
                s.learn();
            } else if (person instanceof Teacher t) {
                t.teach();
            }
        } else if (animal instanceof Dog dog) {
            dog.eat();
        }
        animal.sleep();
    }

    private static void stringExamples() {
        String originalText = "Contrary to popular belief, Lorem Ipsum is not simply random text. " +
                "\nIt has roots in a piece of classical Latin literature from 45 BC, making it" +
                "\nover 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia," +
                "\nlooked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, " +
                "\nand going through the cites of the word in classical literature, discovered the undoubtable " +
                "\nsource. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" " +
                "\n(The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory " +
                "\nof ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor " +
                "\nsit amet..\", comes from a line in section 1.10.32.\n" +
                "\n" + "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested." +
                "\nSections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in " +
                "\ntheir exact original form, accompanied by English versions from the 1914 translation by H. Rackham.";

        String indentedText = StringUtils.indentText(originalText, 10);

        System.out.println(indentedText);

        String transformedText = StringUtils.applyFunction(originalText, String::toUpperCase);

        System.out.println(transformedText);

        String textBlock = """
                Contrary to popular belief, Lorem Ipsum is not simply random text.
                          It has roots in a piece of classical Latin literature from 45 BC, making it
                          over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia,
                          looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage,
                          and going through the cites of the word in classical literature, discovered the undoubtable
                          source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum"
                          (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory
                          of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor
                          sit amet..", comes from a line in section 1.10.32.

                          The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.
                          Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in
                          their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.
                """;

        System.out.println(textBlock);
    }

    private static void numberFormatExamples() {
        System.out.println(NumberFormattingUtils.compactNumber(1000));
        System.out.println(NumberFormattingUtils.compactNumber(1000000));
        System.out.println(NumberFormattingUtils.compactNumber(1000000000));
    }

    private static void switchExamples() {
        //switch expressions (Preview)
        //before Java 12
        preJava12TypeOfDay(3);

        //Java 12 onwards
        afterJava12TypeOfDay(7);
        afterJava12TypeOfDay(100);
    }

    private static void preJava12TypeOfDay(int day) {
        String typeOfDay;
        switch (day) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                typeOfDay = "Weekday";
                break;
            case 6:
            case 7:
                typeOfDay = "Weekend";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        System.out.println(typeOfDay);
    }

    private static void afterJava12TypeOfDay(int day) {
        String typeOfDay = switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> throw new IllegalArgumentException("Invalid day of the week: " + day);
        };
        System.out.println(typeOfDay);
    }
}