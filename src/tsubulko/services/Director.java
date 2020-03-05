package tsubulko.services;

import tsubulko.entity.Person;

public class Director {
    PersonList personList;

    public Director(PersonList personList) {
        this.personList = personList;
    }

    public boolean addPerson(Person person){
        return personList.getPersons().add(person);
    }

    public boolean deletePerson(int id){
        for (Person person :
                personList.getPersons()) {
            if (person.getId() == id) {
                return personList.getPersons().remove(person);
            }
        }
        return false;
    }



    public void printPerson(){
        for (Person person:
                personList.getPersons() ) {
            System.out.println(person);
        }
    }
}
