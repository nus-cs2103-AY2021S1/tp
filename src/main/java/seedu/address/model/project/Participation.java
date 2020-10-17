package seedu.address.model.project;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.task.Task;

/**
 * Participation class handles the interactions between the different classes involved in the project.
 */
public class Participation {

    /**
     * List of thing(s) Person can participate in.
     */
    private String person;
    private String project;
    private Role role;
    private Set<Task> tasks;
    private Set<Meeting> meetings;

    /**
     * Constructor for Participation
     */
    public Participation(String person, String project) {
        this.person = person;
        this.project = project;
        role = Role.MEMBER;
        tasks = new HashSet<>();
        meetings = new HashSet<>();
    }

    /**
     * Alternative constructor that allows specifying the role of the person
     */
    public Participation(String person, String project, Role role) {
        this.person = person;
        this.project = project;
        this.role = role;
        tasks = new HashSet<>();
        meetings = new HashSet<>();
    }
    public void changeRole(Role role) {
        this.role = role;
    }
    /**
     * Indicates attendance for the meeting.
     *
     * @param meeting meeting to attend
     */
    public void attends(Meeting meeting) {
        meetings.add(meeting);
        this.getProject().addMeeting(meeting);
    }

    /**
     * Assigns task to the person
     * @param task  task to be assigned
     */
    public void addTask(Task task) {
        tasks.add(task);
        this.getProject().addTask(task);
    }
    /**
     * Checks whether the person is an attendee of the meeting.
     * @param meeting   meeting to check
     * @return  true if the person is an attendee of the meeting, and false otherwise
     */
    public boolean isAttendeeOf(Meeting meeting) {
        return meetings.contains(meeting);
    }

    /**
     * Checks whether the person has the given task.
     * @param task  the task to check
     * @return  true if the person is assigned to do the task, and false otherwise.
     */
    public boolean hasTask(Task task) {
        return tasks.contains(task);
    }
    public Person getPerson() {
        Person p = null;
        for(int i = 0 ; i<Person.allPeople.size();i++){
            if(Person.allPeople.get(i).getPersonName().toString().equals(person)){
                p = Person.allPeople.get(i);
            }
        }
        return p;
    }
    public PersonName getAssigneeName() {
        return this.getPerson().getPersonName();
    }
    public Project getProject() {
        Project p = null;
        for(int i = 0 ; i<Project.allProjects.size();i++){
            if(Project.allProjects.get(i).getProjectName().toString().equals(project)){
                p = Project.allProjects.get(i);
            }
        }
        return p;
    }

    public Role getRole() {
        return role;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }
}

