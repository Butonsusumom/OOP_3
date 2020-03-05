package sample;

import junit.framework.TestCase;

import org.junit.jupiter.api.Test;
import tsubulko.entity.Adress;
import tsubulko.entity.Birth;
import tsubulko.entity.Person;
import tsubulko.entity.Student;

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
}