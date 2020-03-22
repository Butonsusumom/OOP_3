package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;

import org.junit.jupiter.api.Test;
import serialization.BinarConverter;
import serialization.JsonConverter;
import serialization.TextConverter;
import tsubulko.entity.*;

import java.io.IOException;

public class ControllerTest extends TestCase {

    Controller controller= new Controller();

    @Test
    public void testGetPerson()  {
        assertNull("Shouldn't be found",controller.getPerson(-1));

    }

    @Test
    public void testDeletePerson()  {
        controller.personData.add(new Student(0, new Birth(14, Birth.Month.OCT,2000), "Ksenia", "Tsubulko", Person.Sex.Female, "tsubulko.ksenia@gmail.com", new Adress("Minsk", "Perehyadnaya", 12), Person.Position.Student, "BSUIR", "KSIS", "SOIT", 2, "851002"));
        assertTrue( controller.deletePerson(0));
    }

    @Test
    public void testFillPersonData()  {
        controller.fillPersonData();
        int count=0;
        for (Person person :
                controller.personData) {
          count++;
        }
        assertEquals("Not equals",5,count);
    }

//------------------------------------------------- SERIALIZATION--------------------------------
    @Test
    public void testJsonSerialization() throws IOException {
        ObservableList<Person> result = FXCollections.observableArrayList();
        Manager man=new Manager(1, new Birth(18, Birth.Month.DEC, 1973), "Dmitry", "Tsubulko", Person.Sex.Male, "TiMiQ@gmail.com", new Adress("Minsk", "Soltisa", 14), Person.Position.Manager, 6000, 20, Employee.Education.Hight, 1000);
        controller.personData.add(man);
        JsonConverter conv = new JsonConverter();
        conv.serialise(controller.personData,"persondata.json");
        result = conv.deserialise("persondata.json");
        assertEquals("Not equals",result,controller.personData);
    }

    @Test
    public void testBinarSerialization() throws Exception {
        ObservableList<Person> result = FXCollections.observableArrayList();
        Manager man = new Manager(1, new Birth(18, Birth.Month.DEC, 1973), "Dmitry", "Tsubulko", Person.Sex.Male, "TiMiQ@gmail.com", new Adress("Minsk", "Soltisa", 14), Person.Position.Manager, 6000, 20, Employee.Education.Hight, 1000);
        controller.personData.add(man);
        BinarConverter conv = new BinarConverter();
        conv.serialise(controller.personData, "binaryuser.dat");
        result = conv.deserialise("binaryuser.dat");
        assertEquals("Not equals", result, controller.personData);
    }

    @Test
    public void testTextSerialization() throws Exception {
        ObservableList<Person> result = FXCollections.observableArrayList();
        Manager man = new Manager(1, new Birth(18, Birth.Month.DEC, 1973), "Dmitry", "Tsubulko", Person.Sex.Male, "TiMiQ@gmail.com", new Adress("Minsk", "Soltisa", 14), Person.Position.Manager, 6000, 20, Employee.Education.Hight, 1000);
        controller.personData.add(man);
        TextConverter conv = new TextConverter();
        conv.serialise(controller.personData, "textpers.csv");
        result = conv.deserialise("textpers.csv");
        assertEquals("Not equals", result, controller.personData);
    }
}