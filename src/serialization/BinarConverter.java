package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tsubulko.entity.Person;

import java.io.*;
import java.util.ArrayList;

public class BinarConverter implements Serialization {
    @Override
    public void serialise(ObservableList<Person> data,String name) throws IOException {
        FileOutputStream fileOutput = new FileOutputStream(name);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(new ArrayList<Person>(data));
        fileOutput.close();
        outputStream.close();
    }

    @Override
    public ObservableList<Person> deserialise(String name) throws Exception {
        FileInputStream fiStream = new FileInputStream(name);
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);
        Object object = objectStream.readObject();
        fiStream.close();
        objectStream.close();
        ObservableList<Person> result = FXCollections.observableArrayList();
        result.setAll((ArrayList<Person>)object);
        return result;
    }
}
