package tsubulko.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Adress {
    private String city;
    private String street;
    private int house;

}
