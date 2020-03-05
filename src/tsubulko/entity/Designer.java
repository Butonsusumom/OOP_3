package tsubulko.entity;

import tsubulko.entity.Engineer;
import lombok.*;

import java.util.Date;
import java.util.LinkedList;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Designer extends Engineer {
    public enum DezSkills {
        HTML,
        CSS,
        Illustrator,
        InDesign;
    }

    public enum DezType {
        Game,
        WEB,
        Graphic;
    }
    private DezSkills dezSkills;
    private DezType dezType;

    public Designer(int id,Birth dateOfBirth, String name, String surname, Sex sex, String email, Adress adress, Position position, double salary, int experiense, Education education, String project, DezSkills dezSkills, DezType dezType) {
        super(id,dateOfBirth, name, surname, sex, email, adress, position, salary, experiense, education, project);
        this.dezSkills = dezSkills;
        this.dezType = dezType;
    }
}
