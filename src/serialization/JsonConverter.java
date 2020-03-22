package serialization;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tsubulko.entity.Person;

import javax.swing.text.Utilities;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JsonConverter implements Serialization{
    @Override
    public void serialise(ObservableList<Person> data,String name) throws IOException {
        String baseFile = name;
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        String jsonuser = mapper.writeValueAsString(data);
        mapper.writeValue(new File(baseFile),  new ArrayList<Person>(data));
        System.out.println(jsonuser);


    }

    @Override
    public ObservableList<Person> deserialise(String name) throws IOException {
        String jsonuser = "";
        Scanner in = new Scanner(new File(name));
        while(in.hasNext())
            jsonuser += in.nextLine();
        in.close();
        System.out.println(jsonuser);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class,Person.class);
        ArrayList<Person> arrayListResult = mapper.readValue(jsonuser, type);

        ObservableList<Person> result = FXCollections.observableArrayList();
        result.setAll(arrayListResult);
        System.out.println(result);
        return result;
    }
}
