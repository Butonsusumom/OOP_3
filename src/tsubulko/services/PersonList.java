package tsubulko.services;

import tsubulko.entity.Person;

import java.util.LinkedList;
import java.util.List;

public class PersonList {
    private List<Person> persons = new LinkedList<>();
    public List<Person> getPersons() {
        return persons;
    }
    public void setContacts(List<Person> contacts) {
        this.persons = persons;
    }
}
