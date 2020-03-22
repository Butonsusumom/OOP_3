package tsubulko.entity;

import lombok.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Adress implements Serializable {
    private String city;
    private String street;
    private int house;

    @Override
    public String toString() {
        return "Adress{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                '}';
    }

    public Adress(String city, String street, int house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }
}
