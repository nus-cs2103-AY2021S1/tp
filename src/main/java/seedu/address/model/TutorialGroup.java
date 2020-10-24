package seedu.address.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;

public class TutorialGroup implements Showable<TutorialGroup> {
    private final String id;
    private Module module;
    private HashMap<StudentId, Student> studentList;
    private LocalTime startTime;
    private LocalTime endTime;
    private int durationInHours;

    /**
     * Constructor for Tutorial Group
     * @param id of Tutorail Group
     * @param module that Tutorial Group belongs to
     */
    public TutorialGroup(String id, Module module) {
        this.id = id;
        this.module = module;
        this.studentList = new HashMap<>();
    }


    /**
     * Constructor for Tutorial Group
     * @param id
     */
    public TutorialGroup(String id) {
        this.id = id;
        this.studentList = new HashMap<>();
    }

    //GETTERS

    public String getId() {
        return this.id;
    }

    public Module getModule() {
        return this.module;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDurationInHours() {
        return this.durationInHours;
    }


    public ArrayList<Student> getStudentList() {
        ArrayList<Student> returnList = new ArrayList<Student>();
        returnList.addAll(studentList.values());
        return returnList;
    }


    //SETTERS
    public void setLessonTime(LocalTime startTime, LocalTime endTime, int durationInHours) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInHours = durationInHours;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = new HashMap<StudentId, Student>(); //Refresh the student list
        for (Student student : studentList) {
            this.studentList.put(student.getStudentId(), student);
        }
    }

    //ADD

    /**
     * By right this method should just take in a studentId,
     * then we search the Library of students for the correct instance
     * But the Library has not been implemented yet so this will do for now
     *
     * @param student
     */
    public void addStudent(Student student) {
        this.studentList.put(student.getStudentId(), student);
    }

    //DELETE
    public void deleteStudent(Student student) {
        this.studentList.remove(student.getStudentId());
    }

    /**
     * Checks if two TutorialGroups are the same by first checking if they are the same object,
     * then checking if there have the same id.
     *
     * @param otherTutorialGroup to check against
     * @return true if same, false if not
     */
    public boolean isSame(TutorialGroup otherTutorialGroup) {
        if (otherTutorialGroup == this) {
            return true;
        }

        return otherTutorialGroup != null
            && otherTutorialGroup.getId().equals(getId());
    }
}
