package tsubulko.entity;

import tsubulko.entity.Person;
import lombok.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Student extends Person implements Serializable {
    private String university;
    private String faculty;
    private String specialisation;
    private int course;
    private String group;

    public Student(int id, Birth dateOfBirth, String name, String surname, Person.Sex sex, String email, Adress adress, Person.Position position, String university, String faculty, String specialisation, int course, String group) {
        super(id,dateOfBirth, name, surname, sex, email, adress, position);
        this.university = university;
        this.faculty = faculty;
        this.specialisation = specialisation;
        this.course = course;
        this.group = group;
    }


    @Override
    public String toString() {
        return "Student{" +
                "university='" + university + '\'' +
                ", faculty='" + faculty + '\'' +
                ", specialisation='" + specialisation + '\'' +
                ", course=" + course +
                ", group=" + group +
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

        university+
        COMMA_DELIMITER+
        faculty+
        COMMA_DELIMITER+
        specialisation+
        COMMA_DELIMITER+
        String.valueOf(course)+
        COMMA_DELIMITER+
        group;
        return result;
    }


    public static Student read(String[] tokens) throws IOException{
        Student pers = new Student(Integer.parseInt(tokens[0]),
                new Birth(Integer.parseInt(tokens[1]),Birth.Month.valueOf(tokens[2]),Integer.parseInt(tokens[3])),
                tokens[4],
                tokens[5],
                Person.Sex.valueOf(tokens[6]),
                tokens[7],
                new Adress(tokens[8],tokens[9],Integer.parseInt(tokens[10])),
                Person.Position.valueOf(tokens[11]),
                tokens[12],
                tokens[13],
                tokens[14],
                Integer.parseInt(tokens[15]),
                tokens[16]);
        return pers;
    }
}
