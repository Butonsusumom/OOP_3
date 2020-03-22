package tsubulko.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import tsubulko.entity.Person;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
@Data
@ToString
/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Engineer.class, name = "engineer"),
        @JsonSubTypes.Type(value = Manager.class, name = "manager")
})*/
public class Employee extends Person implements Serializable {
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

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", experiense=" + experiense +
                ", education=" + education +
                "} " + super.toString();
    }
}
