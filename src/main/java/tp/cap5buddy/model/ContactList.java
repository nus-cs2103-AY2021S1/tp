package tp.cap5buddy.model;

import static java.util.Objects.requireNonNull;

import tp.cap5buddy.model.person.Person;
import tp.cap5buddy.model.person.PersonList;
import tp.cap5buddy.model.person.exceptions.DuplicatePersonException;
import tp.cap5buddy.model.person.exceptions.PersonNotFoundException;

public class ContactList {

    private final PersonList persons;

    public ContactList(PersonList persons) {
        this.persons = persons;
    }

    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void editPerson(Person originalPerson, Person editedPerson) throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedPerson);
        persons.editPerson(originalPerson, editedPerson);
    }

    public void removePerson(Person toRemove) {
        persons.remove(toRemove);
    }

    public PersonList getPersonList() {
        return this.persons;
    }
}
