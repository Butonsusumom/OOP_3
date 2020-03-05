package tsubulko.entity;

import tsubulko.entity.Employee;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;

@Data
abstract class Engineer extends Employee {
   // private Project project;
   private String project;

    public Engineer(int id,Birth dateOfBirth, String name, String surname, Sex sex, String email, Adress adress, Position position, double salary, int experiense, Education education, String project) {
        super(id,dateOfBirth, name, surname, sex, email, adress, position, salary, experiense, education);
        this.project = project;
    }


    public Engineer() {
    }
}
