package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tsubulko.entity.*;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextConverter implements Serialization  {
    @Override
    public void serialise(ObservableList<Person> data,String name) throws IOException {
        FileWriter writer = new FileWriter(name);
        String NEW_LINE_SEPARATOR = "\n";

        for (Person person: data) {
            writer.append(person.write());
            writer.append(NEW_LINE_SEPARATOR);
        }
        writer.flush();
        writer.close();
        System.out.println(data);
    }

    @Override
    public ObservableList<Person> deserialise(String name) throws IOException, ClassNotFoundException {
        ObservableList<Person> newList =  FXCollections.observableArrayList();
        String line = "";
        String COMMA_DELIMITER = ",";
        BufferedReader fileReader = new BufferedReader(new FileReader(name));
        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) {
                switch (tokens[11]) {
                    case ("Manager"):
                        newList.add(Manager.read(tokens));
                        break;
                    case ("Programmer"):
                        newList.add(Programmer.read(tokens));
                        break;
                    case ("Tester"):
                        newList.add(Tester.read(tokens));
                        break;
                    case ("Designer"):
                        newList.add(Designer.read(tokens));
                        break;
                    case ("Student"):
                        //newList.add(Student.read(tokens));
                        break;
                }
               // System.out.println(pers);
            }
        }
        System.out.println(newList);
        fileReader.close();
        return newList;

    }
}
