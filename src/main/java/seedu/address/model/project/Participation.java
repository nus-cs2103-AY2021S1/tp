package seedu.address.model.project;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

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
    private Set<Task> tasks;

    /**
     * Constructor for Participation
     */
    public Participation(Person person, Project project) {
        this.person = person;
        this.project = project;
        role = Role.MEMBER;
        tasks = new HashSet<>();
    }

    enum Role {
        LEADER, MEMBER;
    }

}

