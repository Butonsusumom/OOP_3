package tsubulko.entity;

import tsubulko.entity.Engineer;
import tsubulko.entity.Manager;
import lombok.*;

import java.util.LinkedList;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Project {
    private Manager manager;
    private LinkedList<Engineer> engineers;
}
