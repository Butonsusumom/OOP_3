package tsubulko.entity;

import tsubulko.entity.Engineer;
import lombok.*;

import java.util.Date;
import java.util.LinkedList;

@Data

@EqualsAndHashCode
@ToString
public class Programmer extends Engineer {
    public enum Category {
        Junior,
        Middle,
        Senior;
    }

    public enum ProgSkills {
        C,
        JAVA,
        SWIFT;
    }

    private Category category;
    private ProgSkills progSkills;

    public Programmer(int id,Birth dateOfBirth, String name, String surname, Sex sex, String email, Adress adress, Position position, double salary, int experiense, Education education, String project, Category category, ProgSkills progSkills) {
        super(id,dateOfBirth, name, surname, sex, email, adress, position, salary, experiense, education, project);
        this.category = category;
        this.progSkills = progSkills;
    }

    public Programmer() {
    }
}
