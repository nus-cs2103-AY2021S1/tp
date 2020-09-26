package seedu.address.model.project;

import seedu.address.model.person.Person;

/**
 * Participation class handles the interactions between the different classes involved in the project.
 */
public class Participation {

    /**
     * List of thing(s) Person can participate in.
     */
    private Person person;
    private Project project;
    private Role role;

    /**
     * Constructor for Participation
     */
    public Participation(Person person, Project project) {
        this.person = person;
        this.project = project;
        role = Role.MEMBER;
    }

    enum Role {
        LEADER, MEMBER;
    }

}

