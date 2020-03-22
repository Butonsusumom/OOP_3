package tsubulko.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lombok.*;
import tsubulko.entity.Adress;

import java.io.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class, name = "student"),
        @JsonSubTypes.Type(value = Employee.class, name = "employee")
})*/
public abstract class Person implements Serializable {
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

    public String write() throws IOException{
        return "";
    };
}
