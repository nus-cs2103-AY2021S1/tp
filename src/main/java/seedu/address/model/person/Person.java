package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.model.participation.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.task.Task;

/**
 * Represents a Teammate in the team.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // List of all Persons
    private static ArrayList<Person> allPeople = new ArrayList<>();

    // Identity fields
    private PersonName personName;
    private GitUserName gitUserName;
    private Phone phone;
    private Email email;

    // Data fields
    private Address address;
    private HashMap<ProjectName, Participation> listOfParticipations = new HashMap<>();

    /**
     * Every field must be present and not null.
     */
    public Person(PersonName personName, GitUserName gitUserName, Phone phone, Email email, Address address) {
        requireAllNonNull(personName, phone, email, address);
        this.personName = personName;
        this.gitUserName = gitUserName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        allPeople.add(this);
    }

    /**
     * Overloaded constructor to take in listOfParticipations
     */
    public Person(PersonName personName, GitUserName gitUserName, Phone phone, Email email, Address address,
                  HashMap<ProjectName, Participation> listOfParticipations) {
        requireAllNonNull(personName, gitUserName, phone, email, address);
        this.personName = personName;
        this.gitUserName = gitUserName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.listOfParticipations = listOfParticipations;
    }

    public PersonName getPersonName() {
        return personName;
    }

    public GitUserName getGitUserName() {
        return gitUserName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getGitUserNameString() {
        return gitUserName.toString();
    }

    public static ArrayList<Person> getAllPeople() {
        return allPeople;
    }

    public static void setPerson(Person person) {
        for (int i = 0; i < allPeople.size(); i++) {
            String gitUserName = allPeople.get(i).getGitUserNameString();
            String toFind = person.getGitUserNameString();
            if (gitUserName.equals(toFind)) {
                allPeople.set(i, person);
            }
        }
    }

    public HashMap<ProjectName, Participation> getParticipations() {
        return this.listOfParticipations;
    }

    /**
     * Gets all projects that this person participates in.
     */
    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        listOfParticipations.values().forEach(p -> projects.add(p.getProject()));
        return projects;
    }

    /**
     * Gets a person from the universal list of persons from the GitUserName
     */
    public static Person getPersonFromList(GitUserIndex gitUserIndex) {
        for (Person p : allPeople) {
            String indexToCompare = p.getGitUserNameString();
            String gitUserName = gitUserIndex.getGitUserNameString();
            if (indexToCompare.equals(gitUserName)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets all tasks that this person participates in.
     */
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        listOfParticipations.values().forEach(p -> tasks.addAll(p.getTasks()));
        return tasks;
    }

    /**
     * Checks whether Teammate is present in allPeople list.
     * @param gitUserIndex of teammate
     * @return boolean to indicate presence.
     */
    public static boolean isPresent(GitUserIndex gitUserIndex) {
        GitUserName toCompare = gitUserIndex.getGitUserName();
        for (Person p : allPeople) {
            if (toCompare.equals(p.getGitUserName())) {
                return true;
            }
        }
        return false;
    }

    public void updatePersonName(String newPersonNameStr) {
        personName = new PersonName(newPersonNameStr);
    }

    public void updateAddress(String newAddressStr) {
        address = new Address(newAddressStr);
    }

    public void updatePhone(String newPhonestr) {
        phone = new Phone(newPhonestr);
    }

    public void updateEmail(String newEmailStr) {
        email = new Email(newEmailStr);
    }

    /**
     * Adds a project as a Participation to the Person.
     */
    public void addProject(Project p) {
        listOfParticipations.put(p.getProjectName(),
                new Participation(personName.toString(), p.getProjectName().toString()));
    }

    public static void deletePersonFromList(Person person) {
        allPeople.remove(person);
    }

    public void addExistingParticipation(Participation p) {
        listOfParticipations.put(p.getProject().getProjectName(), p);
    }

    /**
     * Returns true if both teammates of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two teammates.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getGitUserName().equals(getGitUserName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && (otherPerson.getPersonName().equals(getPersonName())
                || otherPerson.getAddress().equals(getAddress()));
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherProject = (Person) other;
        return otherProject.getPersonName().equals(getPersonName())
                && otherProject.getGitUserName().equals(getGitUserName())
                && otherProject.getPhone().equals(getPhone())
                && otherProject.getEmail().equals(getEmail())
                && otherProject.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(personName, gitUserName, phone, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Person name: ")
                .append(getPersonName())
                .append(" Git Username: ")
                .append(getGitUserName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }
}
