package tsubulko.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Birth implements Serializable {
   public enum Month{
        JAN,
        FEB,
        MAR,
        APR,
        MAY,
        JUN,
        JUL,
        AUG,
        SEP,
        OCT,
        NOV,
        DEC;
    }


    private int day;
    private Month month;
    private int year;

    @Override
    public String toString() {
        return "Birth{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public  void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeInt(day);
        aOutputStream.writeUTF(String.valueOf(month));
        aOutputStream.writeInt(year);
    }

}
