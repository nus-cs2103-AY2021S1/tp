package seedu.address.logic;

import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.project.Name;
import seedu.address.model.project.Project;

import java.lang.reflect.Member;
import java.util.HashMap;

/**
 * Participation class handles the interactions between the different classes involved in the project.
 */
public class Participation {

    /**
     * List of thing(s) Person can participate in.
     */
    private Person person;
    private Project project;

    /**
     * Constructor for Participation
     */
    public Participation(Person person, Project project) {
        this.person = person ;
        this.project = project;
    }

    enum Role {
        LEADER, MEMBER;
    }

}

