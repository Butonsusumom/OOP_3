package tsubulko.entity;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lombok.*;
import tsubulko.entity.Adress;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Person {
    public enum Sex {
        Male,
        Female;
    }

    public enum Position {
        Student,
        Manager,
        Designer,
        Programmer,
        Tester;
    }

    private Integer id;
    private Birth dateOfBirth;
    private String name;
    private String surname;
    private Sex sex;
    private String email;
    private Adress adress;
    private Position position;
}
