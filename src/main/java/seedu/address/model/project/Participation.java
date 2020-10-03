package seedu.address.model.project;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
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
    private Set<Meeting> meetings;

    /**
     * Constructor for Participation
     */
    public Participation(Person person, Project project) {
        this.person = person;
        this.project = project;
        role = Role.MEMBER;
        tasks = new HashSet<>();
        meetings = new HashSet<>();
    }

    /**
     * indicates attendance for the meeting
     *
     * @param meeting meeting to attend
     */
    public void attends(Meeting meeting) {
        meeting.addAttendee(person);
        meetings.add(meeting);
        project.getMeetings().add(meeting);
    }

    enum Role {
        LEADER, MEMBER;
    }

}

