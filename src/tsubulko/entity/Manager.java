package tsubulko.entity;

import lombok.*;

import java.util.Date;
import java.util.LinkedList;
@Data
@EqualsAndHashCode
@ToString
public class Manager extends Employee {
    private double bonus;
    //private LinkedList<Project> projects;


    public Manager(int id,Birth dateOfBirth, String name, String surname, Sex sex, String email, Adress adress, Position position, double salary, int experiense, Education education, double bonus) {
        super(id,dateOfBirth, name, surname, sex, email, adress, position, salary, experiense, education);
        this.bonus = bonus;
    }

    public Manager() {
    }
}
