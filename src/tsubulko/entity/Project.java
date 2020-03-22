package tsubulko.entity;

import tsubulko.entity.Engineer;
import tsubulko.entity.Manager;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedList;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Project implements Serializable {
    private Manager manager;
    private LinkedList<Engineer> engineers;

    @Override
    public String toString() {
        return "Project{" +
                "manager=" + manager +
                ", engineers=" + engineers +
                '}';
    }
}
