package seedu.address.logic;

import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.project.Name;
import seedu.address.model.project.Project;

import java.util.HashMap;

/**
 * Participation class handles the interactions between the different classes involved in the project.
 */
public class Participation {

    /**
     * List of things Person can participate in.
     */
    private HashMap<PersonName, Person> memberList;
    private HashMap<Name, Project> projectList;
    /*
    for when we add the following activities that a Person can participate in
     */
//    private HashMap<String, Task> taskList;
//    private HashMap<String, Meeting> meetingList;

    enum Role{
        LEADER,MEMBER;
    }

    /**
     *Constructor for Participation
     */
    public Participation(){
        memberList = new HashMap<>();
        projectList = new HashMap<>();
        /*
        for when we add the following activities that a Person can participate in
        */
//        taskList = new HashMap<String, Task>();
//        meetingList = new HashMap<String, Meeting>();
    }

    void AddMember(Person p){
        memberList.put(p.getName(), p);
    }

    void AddProject(Project p){
        projectList.put(p.getName(),p);
    }

    public HashMap<PersonName, Person> getMemberList() {
        return memberList;
    }

    public HashMap<Name, Project> getProjectList() {
        return projectList;
    }

}

