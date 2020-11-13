package seedu.address.model.tutorialgroup;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

public class TutorialGroup {

    // Identity fields
    private final TutorialGroupId tutorialGroupId;
    private DayOfWeek dayOfWeek;
    private TimeOfDay startTime;
    private TimeOfDay endTime;

    private final UniqueStudentList students;

    /**
     * Constructor for Tutorial Group
     * @param tutorialGroupId
     */
    public TutorialGroup(TutorialGroupId tutorialGroupId, DayOfWeek dayOfWeek, TimeOfDay startTime, TimeOfDay endTime) {
        requireAllNonNull(tutorialGroupId, dayOfWeek, startTime, endTime);
        assert (startTime.getTime().compareTo(endTime.getTime()) == -1);
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

    public int getTotalStudents() {
        return this.students.count();
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
                && getId().equals(((TutorialGroup) other).getId())
            && getDayOfWeek().equals(((TutorialGroup) other).getDayOfWeek())
            && getStartTime().equals(((TutorialGroup) other).getStartTime())
            && getEndTime().equals(((TutorialGroup) other).getEndTime()); // state check
    }

    @Override
    public String toString() {
        return getId().toString();
    }
}
