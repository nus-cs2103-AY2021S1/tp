package seedu.address.model.tutorialgroup;

import java.time.LocalTime;

import javafx.collections.ObservableList;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;

public class TutorialGroup {

    // Identity fields
    private final TutorialGroupId tutorialGroupId;
    private final UniqueStudentList students;
    private LocalTime startTime;
    private LocalTime endTime;
    private String dayOfWeek;

    /**
     * Constructor for Tutorial Group
     * @param tutorialGroupId
     */
    public TutorialGroup(TutorialGroupId tutorialGroupId, LocalTime startTime, LocalTime endTime, String dayOfWeek) {
        this.tutorialGroupId = tutorialGroupId;
        this.students = new UniqueStudentList();
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Constructor for Tutorial Group
     * @param tutorialGroupId
     */
    public TutorialGroup(TutorialGroupId tutorialGroupId, UniqueStudentList students, String dayOfWeek,
                         LocalTime startTime, LocalTime endTime) {
        this.tutorialGroupId = tutorialGroupId;
        this.students = students;
        this.dayOfWeek = dayOfWeek;
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

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public ObservableList<Student> getStudents() {
        return students.asUnmodifiableObservableList();
    }

    public UniqueStudentList getUniqueStudentList() {
        return students;
    }


    //SETTERS
    public void setLessonTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
        students.addStudent(student);
    }

    //DELETE
    public void deleteStudent(Student student) {
        students.removeStudent(student);
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroup) // instanceof handles nulls
                && getId().equals(((TutorialGroup) other).getId()); // state check

    }

    @Override
    public String toString() {
        return getId().toString();
    }
}
