package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTags.getTypicalTags;
import static seedu.address.testutil.TypicalTags.getTypicalTagsWithLinks;
import static seedu.address.testutil.TypicalTags.getTypicalTagsWithTasks;

import seedu.address.model.Projact;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class TypicalProjact {

    private TypicalProjact() {} // prevents instantiation

    /**
     * Returns an {@code Projact} with all the typical persons.
     */
    public static Projact getTypicalProjact() {
        Projact projact = new Projact();
        for (Person person : getTypicalPersons()) {
            projact.addPerson(person);
        }

        for (Tag tag : getTypicalTags()) {
            projact.addTag(tag);
        }
        return projact;
    }

    public static Projact getTypicalProjactWithLinks() {
        Projact projact = new Projact();
        for (Person person : getTypicalPersons()) {
            projact.addPerson(person);
        }

        for (Tag tag : getTypicalTagsWithLinks()) {
            projact.addTag(tag);
        }
        return projact;
    }

    public static Projact getTypicalProjactWithTasks() {
        Projact projact = new Projact();
        for (Person person : getTypicalPersons()) {
            projact.addPerson(person);
        }

        for (Tag tag : getTypicalTagsWithTasks()) {
            projact.addTag(tag);
        }
        return projact;
    }
}
