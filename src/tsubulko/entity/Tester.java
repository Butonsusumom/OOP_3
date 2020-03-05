package tsubulko.entity;

import tsubulko.entity.Engineer;
import lombok.*;

import java.util.Date;
import java.util.LinkedList;

@Data

@EqualsAndHashCode
@ToString
public class Tester extends Engineer {
    public enum TestType {
        Manual,
        Automated;
    }

    private TestType testType;

    public Tester(int id,Birth dateOfBirth, String name, String surname, Sex sex, String email, Adress adress, Position position, double salary, int experiense, Education education, String project, TestType testType) {
        super(id, dateOfBirth, name, surname, sex, email, adress, position, salary, experiense, education, project);
        this.testType = testType;
    }

    public Tester() {
    }
}
