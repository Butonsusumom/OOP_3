package tsubulko.entity;

import tsubulko.entity.Person;
import lombok.*;

import java.util.Date;
import java.util.LinkedList;
@Data
public class Employee extends Person {
    public enum Education {
        None,
        Middle,
        Hight;
    }

    private double salary;
    private int experiense;
   // private LinkedList<String> workPlaces;
    private Education education;

    public Employee(int id,Birth  dateOfBirth, String name, String surname, Sex sex, String email, Adress adress,Position position, double salary, int experiense, Education education) {
        super(id,dateOfBirth, name, surname, sex, email, adress, position);
        this.salary = salary;
        this.experiense = experiense;
      //  this.workPlaces = workPlaces;
        this.education = education;
    }

    public Employee() {
    }
}
