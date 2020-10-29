package seedu.address.model.tutorialgroup;

import javafx.collections.ObservableList;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;

public class TutorialGroup {

    // Identity fields
    private final TutorialGroupId tutorialGroupId;
    private final UniqueStudentList students;
    private DayOfWeek dayOfWeek;
    private TimeOfDay startTime;
    private TimeOfDay endTime;


    /**
     * Constructor for Tutorial Group
     * @param tutorialGroupId
     */
    public TutorialGroup(TutorialGroupId tutorialGroupId, DayOfWeek dayOfWeek, TimeOfDay startTime, TimeOfDay endTime) {
        this.tutorialGroupId = tutorialGroupId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.students = new UniqueStudentList();
    }

    /**
     * Constructor for Tutorial Group
     * @param tutorialGroupId
     */
    public TutorialGroup(TutorialGroupId tutorialGroupId, DayOfWeek dayOfWeek,
                         TimeOfDay startTime, TimeOfDay endTime, UniqueStudentList students) {
        this.tutorialGroupId = tutorialGroupId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.students = students;
    }

    //GETTERS

    public TutorialGroupId getId() {
        return this.tutorialGroupId;
    }

    public TimeOfDay getStartTime() {
        return this.startTime;
    }

    public TimeOfDay getEndTime() {
        return endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public ObservableList<Student> getStudents() {
        return students.asUnmodifiableObservableList();
    }

    public UniqueStudentList getUniqueStudentList() {
        return students;
    }


    //SETTERS
    public void setLessonTime(TimeOfDay startTime, TimeOfDay endTime) {
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
