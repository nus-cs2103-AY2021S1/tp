package seedu.address.model.participation;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.project.Role;
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

    /**
     * Constructor for Participation
     */
    public Participation(String person, String project) {
        this.person = person;
        this.project = project;
        role = Role.MEMBER;
        tasks = new HashSet<>();
    }

    /**
     * Alternative constructor that allows specifying the role of the person
     */
    public Participation(String person, String project, Role role) {
        this.person = person;
        this.project = project;
        this.role = role;
        tasks = new HashSet<>();
    }

    /**
     * Assigns task to the person
     *
     * @param task task to be assigned
     */
    public void addTask(Task task) {
        tasks.add(task);
        this.getProject().addTask(task);
    }

    /**
     * Removes task from the person
     *
     * @param task task to be removed
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Checks whether the person has the given task.
     *
     * @param task the task to check
     * @return true if the person is assigned to do the task, and false otherwise.
     */
    public boolean hasTask(Task task) {
        return tasks.contains(task);
    }

    public Person getPerson() {
        Person p = null;
        for (int i = 0; i < Person.getAllPeople().size(); i++) {
            if (Person.getAllPeople().get(i).getGitUserNameString().equals(person)) {
                p = Person.getAllPeople().get(i);
            }
        }
        return p;
    }

    public GitUserName getAssigneeName() {
        return this.getPerson().getGitUserName();
    }

    public Project getProject() {
        Project p = null;
        for (int i = 0; i < Project.getAllProjects().size(); i++) {
            if (Project.getAllProjects().get(i).getProjectName().toString().equals(project)) {
                p = Project.getAllProjects().get(i);
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

    public void setPerson(Person person) {
        this.person = person.getGitUserNameString();
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setProject(String project) {
        this.project = project;
    }

    /**
     * deletes all the participation that involves personToDelete.
     *
     * @param model current model.
     * @param personToDelete person whose participations are to be deleted.
     */
    public static void deleteAllParticipationOf(Model model, Person personToDelete) {
        String nameOfPersonToDelete = personToDelete.getGitUserNameString();
        Participation currentParticipation;
        Project currentProject;

        for (int i = 0; i < Project.getAllProjects().size(); i++) {
            currentProject = Project.getAllProjects().get(i);
            if (currentProject.hasParticipation(nameOfPersonToDelete)) {
                currentParticipation = currentProject.getParticipation(nameOfPersonToDelete);
                currentProject.removeParticipation(currentParticipation);
                currentProject.getTasks().forEach(task -> task.removeAssigneeOfName(nameOfPersonToDelete));
                model.deleteParticipation(currentParticipation);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Participation)) {
            return false;
        }

        Participation otherParticipation = (Participation) other;
        return otherParticipation.getProject().equals(getProject())
                && otherParticipation.getPerson().equals(getPerson())
                && otherParticipation.getRole().equals(getRole())
                && otherParticipation.getTasks().equals(getTasks());
    }

    /**
     * Returns true if both Participations contain the same project and person.
     * This defines a weaker notion of equality between two Participations.
     */
    public boolean isSameParticipation(Participation other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getProject().equals(getProject())
                && (other.getPerson().equals(getPerson()));
    }

}

