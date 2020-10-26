package seedu.address.model.tutorialgroup;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.address.model.person.Showable;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;

public class TutorialGroup {

    // Identity fields
    private final TutorialGroupId tutorialGroupId;
    private Module module;
    private HashMap<StudentId, Student> studentList;
    private LocalTime startTime;
    private LocalTime endTime;

    // Data fields
    //private HashMap<StudentId, Student> studentList;

    //

    //    /**
    //     * Constructor for Tutorial Group
    //     * @param id of Tutorial Group
    //     * @param module that Tutorial Group belongs to
    //     */
    //    public TutorialGroup(String id, Module module) {
    //        this.id = id;
    //        this.module = module;
    //        this.studentList = new HashMap<>();
    //    }


    /**
     * Constructor for Tutorial Group
     * @param tutorialGroupId
     */
    public TutorialGroup(TutorialGroupId tutorialGroupId) {
        this.tutorialGroupId = tutorialGroupId;
        this.studentList = new HashMap<>();
    }

    /**
     * Constructor for Tutorial Group
     * @param tutorialGroupId
     * @param startTime
     * @param endTime
     */
    public TutorialGroup(TutorialGroupId tutorialGroupId, LocalTime startTime, LocalTime endTime) {
        this.tutorialGroupId = tutorialGroupId;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    //GETTERS

    public TutorialGroupId getId() {
        return this.tutorialGroupId;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getDurationInHours() {
        return (this.startTime.until(endTime, ChronoUnit.MINUTES)) / 60.0;
    }


    public List<Student> getStudentList() {
        ArrayList<Student> returnList = new ArrayList<>();
        returnList.addAll(studentList.values());
        return returnList;
    }


    //SETTERS
    public void setLessonTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = new HashMap<StudentId, Student>(); //Refresh the student list
        for (Student student : studentList) {
            this.studentList.put(student.getStudentId(), student);
        }
    }

    public int getTotalStudents() {
        return this.studentList.size();
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
