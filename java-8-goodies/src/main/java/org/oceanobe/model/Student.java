package org.oceanobe.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Student {
    private String name;
    private int age;
    private double grade;

    private LocalDate enrollmentDate;

    private Date graduationDate;

    public Student(String name, int age, double grade, LocalDate enrollmentDate, Date graduationDate) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.enrollmentDate = enrollmentDate;
        this.graduationDate = graduationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        if (Double.compare(student.grade, grade) != 0) return false;
        if (!Objects.equals(name, student.name)) return false;
        if (!Objects.equals(enrollmentDate, student.enrollmentDate))
            return false;
        return Objects.equals(graduationDate, student.graduationDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        temp = Double.doubleToLongBits(grade);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (enrollmentDate != null ? enrollmentDate.hashCode() : 0);
        result = 31 * result + (graduationDate != null ? graduationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", enrollmentDate=" + enrollmentDate +
                ", graduationDate=" + graduationDate +
                '}';
    }
}
