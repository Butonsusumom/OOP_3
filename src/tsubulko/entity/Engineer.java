package tsubulko.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.ToString;
import tsubulko.entity.Employee;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

@Data
/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Programmer.class, name = "programmer"),
        @JsonSubTypes.Type(value = Designer.class, name = "tsubulko.entity.Designer"),
        @JsonSubTypes.Type(value = Tester.class, name = "tester")
})*/
abstract  class  Engineer extends Employee implements Serializable {
   // private Project project;
   private String project;

    public Engineer(int id,Birth dateOfBirth, String name, String surname, Sex sex, String email, Adress adress, Position position, double salary, int experiense, Education education, String project) {
        super(id,dateOfBirth, name, surname, sex, email, adress, position, salary, experiense, education);
        this.project = project;
    }


    public Engineer() {
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "project='" + project + '\'' +
                "} " + super.toString();
    }
}
