package tsubulko.entity;

import tsubulko.entity.Engineer;
import lombok.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

@Data
@EqualsAndHashCode
@ToString
public class Programmer extends Engineer implements Serializable {
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

    @Override
    public String toString() {
        return "Programmer{" +
                "category=" + category +
                ", progSkills=" + progSkills +
                "} " + super.toString();
    }

    @Override
    public String write() throws IOException {
        String COMMA_DELIMITER = ",";
        String result=
                String.valueOf(super.getId())+
                        COMMA_DELIMITER+
                        String.valueOf(super.getDateOfBirth().getDay())+
                        COMMA_DELIMITER+
                        String.valueOf(super.getDateOfBirth().getMonth())+
                        COMMA_DELIMITER+
                        String.valueOf(super.getDateOfBirth().getYear())+
                        COMMA_DELIMITER+
                        super.getName()+
                        COMMA_DELIMITER+
                        super.getSurname()+
                        COMMA_DELIMITER+
                        String.valueOf(super.getSex())+
                        COMMA_DELIMITER+
                        super.getEmail()+
                        COMMA_DELIMITER+
                        super.getAdress().getCity()+
                        COMMA_DELIMITER+
                        super.getAdress().getStreet()+
                        COMMA_DELIMITER+
                        String.valueOf(super.getAdress().getHouse())+
                        COMMA_DELIMITER+
                        String.valueOf(super.getPosition())+
                        COMMA_DELIMITER+

                        String.valueOf(super.getSalary())+
                        COMMA_DELIMITER+
                        String.valueOf(super.getExperiense())+
                        COMMA_DELIMITER+
                        super.getEducation()+
                        COMMA_DELIMITER+
                        super.getProject()+
                        COMMA_DELIMITER+

                        String.valueOf(category)+
                        COMMA_DELIMITER+
                        String.valueOf(progSkills);
        return result;
    }


    public static Programmer read(String[] tokens) throws IOException{
        Programmer pers = new Programmer(Integer.parseInt(tokens[0]),
                new Birth(Integer.parseInt(tokens[1]),Birth.Month.valueOf(tokens[2]),Integer.parseInt(tokens[3])),
                tokens[4],
                tokens[5],Person.Sex.valueOf(tokens[6]),
                tokens[7],
                new Adress(tokens[8],tokens[9],Integer.parseInt(tokens[10])),
                Person.Position.valueOf(tokens[11]),
                Double.parseDouble(tokens[12]),
                Integer.parseInt(tokens[13]),
                Employee.Education.valueOf(tokens[14]),
                tokens[15],
                Programmer.Category.valueOf(tokens[16]),
                Programmer.ProgSkills.valueOf(tokens[17]));
        return pers;
    }
}
